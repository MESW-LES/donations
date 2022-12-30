import { initializeApp } from "firebase/app";

const firebaseCredentials = {
  apiKey: "AIzaSyCPVqRLFvjOwZ4vD6fxjInqO7L8dRG3UDc",
  authDomain: "donations-app-les",
  projectId: "95582677203",
};

// if the firebase app does not exist create one
export const initFirebase = () => {
  return initializeApp(firebaseCredentials);
};
