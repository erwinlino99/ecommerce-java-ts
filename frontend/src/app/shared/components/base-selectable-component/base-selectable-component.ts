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
  template: `
    <div class="form-field">
      <label *ngIf="label">{{ label }}</label>
      <select [formControl]="internalControl" class="form-select" [attr.disabled]="readonly ? true : null">
        <option [value]="null" disabled>{{ placeholder }}</option>
        <option *ngFor="let item of items()" [value]="item.name"> {{ item.name }}
        </option>
      </select>
    </div>
  `
})
export class BaseSelectableComponent implements OnInit, OnDestroy, ControlValueAccessor {
  private api = inject(ApiService);
  private destroy$ = new Subject<void>();

  @Input() label: string = '';
  @Input() endpoint: string = '';
  @Input() placeholder: string = 'Seleccione una marca...';
  @Input() readonly: boolean = false;

  public items = signal<SelectableItem[]>([]);
  public internalControl = new FormControl();

  onChange: any = () => {};
  onTouched: any = () => {};

  ngOnInit() {
    if (this.endpoint) this.loadData();

    this.internalControl.valueChanges
      .pipe(takeUntil(this.destroy$))
      .subscribe(value => this.onChange(value));
  }

  private loadData() {
    this.api.get<SelectableItem[]>(this.endpoint).subscribe({
      next: (data) => this.items.set(data || []),
      error: (err) => console.error('Error:', err)
    });
  }

  writeValue(value: any): void {
    this.internalControl.setValue(value, { emitEvent: false });
  }
  registerOnChange(fn: any): void { this.onChange = fn; }
  registerOnTouched(fn: any): void { this.onTouched = fn; }
  setDisabledState(isDisabled: boolean): void {
    isDisabled ? this.internalControl.disable() : this.internalControl.enable();
  }
  ngOnDestroy() { this.destroy$.next(); this.destroy$.complete(); }
}