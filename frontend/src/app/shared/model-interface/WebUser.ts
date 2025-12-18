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
  is_active: 0 | 1;          // tinyint(1)
  is_blocked: 0 | 1;         // tinyint(1)
  last_time_login: string | null; // datetime o null
  data: string | null;       
}
