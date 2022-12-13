import axios from "axios";
import { BackResponse } from "../types/BackResponse";
import { User } from "../types/User";

export default async function doLogin(email: string): Promise<User> {
  try {
    const { data, status } = await axios.post<BackResponse>(
      "http://localhost:8080/login",
      {
        email: email,
      },
      {
        headers: {
          Accept: "application/json",
        },
      }
    );

    return data.message;
  } catch (error: any) {
    throw new Error(error.message);
  }
}
