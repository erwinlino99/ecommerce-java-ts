import { Component } from '@angular/core';
import { BaseListComponent } from '../../../../shared/components/base-list-component/base-list-component';
import { WebUser } from '../../../../shared/model-interface/WebUser';
import { CommonModule, DatePipe } from '@angular/common';
import { MatIcon } from '@angular/material/icon';
import { CpUser } from '../../../../shared/model-interface/CpUser';

@Component({
  selector: 'cp-app-web-users-page',
  imports: [DatePipe, CommonModule, MatIcon],
  templateUrl: './cp-web-users-page.html',
  styleUrl: './../list-page.scss',
})
export class CpUsersPage extends BaseListComponent<WebUser> {
  protected readonly endpoint = '/summary-web-users';
  protected override detailRoutePath = 'web-user-detail';
}
