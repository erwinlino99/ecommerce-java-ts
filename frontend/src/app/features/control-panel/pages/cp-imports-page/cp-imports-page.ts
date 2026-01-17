import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../../../service/api.service';
import { ImportResponse } from '../../../../shared/model-interface/ImportResponse';
import { disableDebugTools } from '@angular/platform-browser';
import { PopupService } from '../../../../service/pop.up.data.service';

@Component({
  selector: 'cp-app-imports-page',
  standalone: true, // Importante para componentes modernos
  imports: [CommonModule],
  templateUrl: './cp-imports-page.html',
  styleUrl: './cp-imports-page.scss',
})
export class CpImportsPage {
  selectedFile: File | null = null;
  isDragging: boolean = false;
  isUploading: boolean = false;
  constructor(private api: ApiService,private popUp:PopupService) {}

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
    formData.append('file', this.selectedFile); // El nombre 'file' debe coincidir con el Backend
    const endpoint = '/file/upload-template';

    this.api.post<ImportResponse>(endpoint, formData).subscribe({
      next: (res: any) => {
        console.log('Archivo procesado con éxito:', res);
        this.isUploading = false;
        this.clearFile();
        alert('Productos importados correctamente');
      },
      error: (err) => {
        this.isUploading = true;
        const msm=err.error?.message;
        const success=err.error?.success;
        const erroresArray: string[] = err.error?.errors || [];
        this.isUploading = false;
        this.popUp.errorMultiple(erroresArray)
      },
    });
  }

  clearFile(): void {
    this.selectedFile = null;
    this.isUploading = false;
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
      console.log('Archivo soltado:', this.selectedFile.name);
    }
  }
}
