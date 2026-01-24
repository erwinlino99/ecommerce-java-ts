import { Component, ViewChild, ElementRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../../../service/api.service';
import { ImportResponse } from '../../../../shared/model-interface/ImportResponse';
import { PopupService } from '../../../../service/pop.up.data.service';
import Swal from 'sweetalert2';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'cp-app-imports-page',
  standalone: true,
  imports: [CommonModule,MatIcon],
  templateUrl: './cp-imports-page.html',
  styleUrl: './cp-imports-page.scss',
})
export class CpImportsPage {
  @ViewChild('fileInput') fileInput!: ElementRef;

  selectedFile: File | null = null;
  isDragging: boolean = false;
  isUploading: boolean = false;

  constructor(
    private api: ApiService,
    private popUp: PopupService,
  ) {}

  downloadTemplate(): void {
    const endpoint = '/file/download-template';
    this.api.get<Blob>(endpoint, { responseType: 'blob' }).subscribe({
      next: (res: Blob) => {
        const url = window.URL.createObjectURL(res);
        const link = document.createElement('a');
        link.href = url;
        link.download = 'plantilla_productos.xlsx';
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        window.URL.revokeObjectURL(url);
      },
      error: (err) => {
        console.log('Error al descargar:', err);
      },
    });
  }

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    if (file) {
      this.selectedFile = file;
      console.log('Archivo seleccionado:', file.name);
    }
  }

  uploadFile(): void {
    if (!this.selectedFile) return;
    this.isUploading = true;

    const formData = new FormData();
    formData.append('file', this.selectedFile);
    const endpoint = '/file/upload-template';

    this.api.post<ImportResponse>(endpoint, formData).subscribe({
      next: (res: any) => {
        this.isUploading = false;
        this.clearFile();

        Swal.fire({
          title: '¡Éxito!',
          text: 'Todos los productos importados/actualizados correctamente',
          icon: 'success',
          confirmButtonText: 'OK',
          confirmButtonColor: '#2e7d32',
        });
      },

      error: (err) => {
        this.isUploading = false;

        const msm = err.error?.message || 'Error en la importación';
        const success = err.error?.success || err.error?.succes || 0;
        const erroresArray: string[] = err.error?.errors || [];

        const erroresHtml =
          erroresArray.length > 0
            ? `<div style="text-align: left; margin-top: 15px; max-height: 200px; overflow-y: auto; background: #fdfdfd; padding: 10px; border: 1px solid #eee; border-radius: 5px;">
            <ul style="list-style-type: none; padding: 0; font-family: monospace; font-size: 0.9em;">
              ${erroresArray.map((e) => `<li style="margin-bottom: 8px; border-bottom: 1px solid #f0f0f0; padding-bottom: 4px;">⚠️ ${e}</li>`).join('')}
            </ul>
           </div>`
            : '';

        Swal.fire({
          title: msm,
          html: `
            <div style="font-size: 1.1em; margin-bottom: 10px;">
              <span style="color: #2e7d32; font-weight: bold;">
                ✅ Registros procesados con éxito: ${success}
              </span>
            </div>
            ${erroresHtml}
          `,
          icon: 'warning',
          confirmButtonText: 'Entendido',
          confirmButtonColor: '#c62828',
          width: '500px',
        });

        // this.clearFile();
      },
    });
  }

  clearFile(): void {
    this.selectedFile = null;
    this.isUploading = false;
    if (this.fileInput && this.fileInput.nativeElement) {
      this.fileInput.nativeElement.value = '';
    }
  }

  onDragOver(event: DragEvent): void {
    event.preventDefault();
    event.stopPropagation();
    this.isDragging = true;
  }

  onDragLeave(): void {
    this.isDragging = false;
  }

  onDrop(event: DragEvent): void {
    event.preventDefault();
    event.stopPropagation();
    this.isDragging = false;

    if (event.dataTransfer?.files && event.dataTransfer.files.length > 0) {
      this.selectedFile = event.dataTransfer.files[0];

      if (this.fileInput) {
        const dataTransfer = new DataTransfer();
        dataTransfer.items.add(this.selectedFile);
        this.fileInput.nativeElement.files = dataTransfer.files;
      }
      console.log('Archivo soltado:', this.selectedFile.name);
    }
  }
}
