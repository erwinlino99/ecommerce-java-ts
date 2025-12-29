export interface WebUser {
  id: number;
  name: string;
  lastName: string;
  email: string;
  password: string;
  cif: string | null;
  created: string;           
  modified: string;          
  deleted: string | null;    // datetime o null
  isActive: 0 | 1;          // tinyint(1)
  isBlocked: 0 | 1;         // tinyint(1)
  last_time_login: string | null; // datetime o null
  data: string | null;       
}
