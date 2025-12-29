export interface CpUser {
  id: number;
  name: string;
  surname: string;
  email: string;
  password: string;
  created: string;           
  modified: string;          
  is_active: 0 | 1;
}
