export type Donee = {
  id?: number;
  company: {
    id?: number;
    name: string;
    description: string;
    taxNumber: string;
    phone: string;
    email: string;
  };
  categoryCodes?: string[];
};
