import axios from "axios";
import { BackResponse } from "../types/BackResponse";
import { Donor } from "../types/Donor";

export default async function doRegister(donor: Donor): Promise<Donor> {
  try {
    const { data, status } = await axios.post<BackResponse>(
      "http://localhost:8080/donors",
      {
        person: donor.person,
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
