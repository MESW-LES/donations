export type Donor = {
  id?: number;
  person: {
    id?: number;
    firstName: string;
    lastName: string;
    nif: string;
    //address: string;
    email: string;
  };
};
