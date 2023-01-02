import axios from "axios";
import { BackResponse } from "../types/BackResponse";
import { Donee } from "../types/Donee";
import { Donor } from "../types/Donor";

export default async function doRegister(donee: Donee): Promise<Donee> {
  try {
    console.log(donee)
    const { data, status } = await axios.post<BackResponse>(
      "http://localhost:8080/donees",
      {
        company: donee.company,
        categoryCodes: donee.categoryCodes,
      },
      {
        headers: {
          Accept: "application/json",
        },
      }
    );

    return data.message;
  } catch (error: any) {
    console.log(error);
    throw new Error(error.message);
  }
}
