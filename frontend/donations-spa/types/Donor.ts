export type Donor = {
  id?: number;
  person: {
    id?: number;
    firstName: string;
    lastName: string;
    nif: string;
    email: string;
  };
};
