import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../../../service/api.service';

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

  constructor(private api: ApiService) {}

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
    console.log('Subiendo archivo al backend...');

    setTimeout(() => {
      this.isUploading = false;
    }, 2000);
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
