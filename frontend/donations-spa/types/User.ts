import { UserCredential } from "firebase/auth";

export type User = {
  user?: UserCredential;
  role?: string;
};
