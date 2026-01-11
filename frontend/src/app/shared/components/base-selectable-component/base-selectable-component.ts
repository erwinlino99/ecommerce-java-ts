import { Component, Input, OnInit, inject, signal, forwardRef, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormControl, NG_VALUE_ACCESSOR, ControlValueAccessor } from '@angular/forms';
import { Subject, takeUntil } from 'rxjs';
import { ApiService } from '../../../service/api.service';
import { SelectableItem } from '../../model-interface/SelectableItem';

@Component({
  selector: 'app-base-selectable',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => BaseSelectableComponent),
      multi: true,
    },
  ],
  templateUrl: './base-selectable.component.html',
  styleUrl: './base-selectable.component.scss'
})
export class BaseSelectableComponent implements OnInit, OnDestroy, ControlValueAccessor {
  private api = inject(ApiService);
  private destroy$ = new Subject<void>();

  @Input() label: string = '';
  @Input() endpoint: string = '';
  @Input() placeholder: string = 'Seleccione una opción...';
  @Input() readonly: boolean = false;

  public items = signal<SelectableItem[]>([]);
  public internalControl = new FormControl();

  onChange: any = () => {};
  onTouched: any = () => {};

  ngOnInit() {
    if (this.endpoint) {
      this.loadData();
    }

    // Notificar al padre cuando el usuario cambia el valor
    this.internalControl.valueChanges
      .pipe(takeUntil(this.destroy$))
      .subscribe((value) => {
        this.onChange(value);
      });
  }

  private loadData() {
    this.api.get<any[]>(this.endpoint).subscribe({
      next: (data) => {
        if (data) {
          const mappedData: SelectableItem[] = data.map((item) => ({
            id: item.id,
            name: item.name || item.label || 'Sin nombre',
          }));
          this.items.set(mappedData);
        }
      },
      error: (err) => console.error(`Error cargando selectable [${this.endpoint}]:`, err),
    });
  }

  // --- Control Value Accessor ---
  writeValue(value: any): void {
    this.internalControl.setValue(value, { emitEvent: false });
  }
  registerOnChange(fn: any): void { this.onChange = fn; }
  registerOnTouched(fn: any): void { this.onTouched = fn; }
  setDisabledState(isDisabled: boolean): void {
    isDisabled ? this.internalControl.disable() : this.internalControl.enable();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}