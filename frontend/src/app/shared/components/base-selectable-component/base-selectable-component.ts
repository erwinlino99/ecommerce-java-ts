import { Component, Input, OnInit, inject, signal, forwardRef, OnDestroy, OnChanges, SimpleChanges } from '@angular/core';
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
    <div class="form-field" style="display: flex; flex-direction: column; gap: 0.5rem; margin-bottom: 1rem; width: 100%;">
      <label *ngIf="label" 
        style="font-family: 'JetBrains Mono', monospace; font-size: 0.75rem; font-weight: 700; color: var(--accent); text-transform: uppercase; letter-spacing: 1px;">
        {{ label }}
      </label>

      <div style="position: relative; display: flex; align-items: center; width: 100%;">
        <select 
          [formControl]="internalControl" 
          [attr.disabled]="readonly ? true : null"
          (blur)="onTouched()"
          style="width: 100%; color: var(--text-primary); border: 1px solid var(--border-ui); border-radius: 8px; padding: 0.7rem 1rem; font-size: 0.9rem; appearance: none; outline: none; transition: all 0.3s ease; font-family: 'Inter', sans-serif;"
          [style.background-color]="readonly ? 'var(--bg-app)' : 'var(--bg-header)'"
          [style.cursor]="readonly ? 'not-allowed' : 'pointer'"
          [style.opacity]="readonly ? '0.6' : '1'"
          [style.pointer-events]="readonly ? 'none' : 'auto'"> <option [value]="null" disabled selected style="background-color: var(--bg-panel); color: var(--text-secondary);">
            {{ placeholder }}
          </option>
          
          <option *ngFor="let item of items()" [value]="item.id" style="background-color: var(--bg-panel); color: var(--text-primary);">
            {{ item.name }}
          </option>
        </select>

        <div style="position: absolute; right: 1rem; pointer-events: none; display: flex; color: var(--text-secondary);">
          <svg width="10" height="6" viewBox="0 0 10 6" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M1 1L5 5L9 1" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
          </svg>
        </div>
      </div>
    </div>
  `
})
export class BaseSelectableComponent implements OnInit, OnDestroy, OnChanges, ControlValueAccessor {
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

  ngOnChanges(changes: SimpleChanges) {
    if (changes['readonly']) {
      this.updateDisabledState(changes['readonly'].currentValue);
    }
  }

  private updateDisabledState(isReadonly: boolean) {
    if (isReadonly) {
      this.internalControl.disable({ emitEvent: false });
    } else {
      this.internalControl.enable({ emitEvent: false });
    }
  }

  ngOnInit() {
    if (this.endpoint) this.loadData();
    this.updateDisabledState(this.readonly);

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

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }
}