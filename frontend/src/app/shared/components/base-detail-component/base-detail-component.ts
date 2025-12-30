import { Component, Directive, inject, OnInit, signal } from '@angular/core';
import { ApiService } from '../../../service/api.service';
import { ActivatedRoute, Router } from '@angular/router'; // CORREGIDO: Router de Angular, no de Express
import { FormBuilder, FormGroup } from '@angular/forms';
import { Observable, tap } from 'rxjs';

@Directive()
export abstract class BaseDetailComponent<T> implements OnInit {
  //PONEMOS ESTADOS --> PARA SABER SI ESTABAMOS EDITANDO O VIENDO
  public item = signal<T | null>(null);
  public isEditing = signal<boolean>(false);
  public loading = signal<boolean>(true);
  public form!: FormGroup;
  //PONERMOS EL OBSERVABLE QUE SERÁ EL OBJETO QUE RECUPEREMOS
  public observable$!: Observable<T>;
  //ENDPOINT AL QUE QUEREMOS APUNTAR PARA LA RECUPERACION/EDICION DEL MISMO
  protected abstract endpoint: string;
  //ESTO ES EL NOMBRE DE LA VARIABLE EN LA URL (ej: 'id', 'productIdDetail')
  protected abstract idParamName: string;
  constructor(
    protected api: ApiService,
    protected route: ActivatedRoute,
    protected router: Router,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.form = this.createForm();
    this.initDataStream();
  }

  //MÉTODO QUE LOS HIJOS DEBEN IMPLEMENTAR PARA DEFINIR SUS CAMPOS DE FORMULARIO
  protected abstract createForm(): FormGroup;

  private initDataStream(): void {
    //RECUPERAMOS EL ID DE LA URL USANDO EL NOMBRE DEFINIDO POR EL HIJO
    const id = this.route.snapshot.paramMap.get(this.idParamName);
    if (id) {
      this.observable$ = this.api.get<T>([this.endpoint, id]).pipe(
        tap((data) => {
          if (data) {
            this.item.set(data);
            this.form.patchValue(data as any);
          }
          this.loading.set(false);
        })
      );

      this.observable$.subscribe({
        next: (data) => {
          console.log('DESDE', this.endpoint, 'DATO ->', data);
        },
        error: (err) => {
          console.error('Error en ApiService:', err);
          this.loading.set(false);
        },
      });
    }
  }

  //MÉTODO PARA GUARDAR LOS CAMBIOS EN LA BASE DE DATOS
  public save(): void {
    if (this.form.valid) {
      //ESTO ES LO QUE RECUPERAMOS
      const id = this.route.snapshot.paramMap.get(this.idParamName);
      if (!id) return;

      //COMBINAMOS EL OBJETO ORIGINAL CON LOS VALORES DEL FORMULARIO
      const payload = { ...this.item(), ...this.form.value };

      this.api.put<T>([this.endpoint, id], payload).subscribe({
        next: (updatedData) => {
          this.item.set(updatedData);
          this.isEditing.set(false);
          this.form.markAsPristine();
          console.log('ACTUALIZADO CON ÉXITO');
        },
        error: (err) => {
          console.error('ERROR AL ACTUALIZAR:', err);
        },
      });
    }
  }

  //MÉTODO PARA CAMBIAR ENTRE MODO VER Y MODO EDITAR
  public toggleEdit(): void {
    this.isEditing.update((val) => !val);

    //SI CANCELAMOS LA EDICIÓN, VOLVEMOS A CARGAR LOS DATOS ORIGINALES EN EL FORM
    if (!this.isEditing() && this.item()) {
      this.form.patchValue(this.item() as any);
    }
  }

  //MÉTODO PARA VOLVER ATRÁS AL LISTADO
  public goBack(): void {
    this.router.navigate(['../../'], { relativeTo: this.route });
  }
}
