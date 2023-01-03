import axios from "axios";
import { BackResponse } from "../types/BackResponse";
import { Pagination } from "../types/Pagination";

const donationsURL: string = "http://localhost:8080/donations";
const applicationJSON: string = "application/json";

export const getCreatedDonations = async (): Promise<Pagination> => {
  return getDonations("?status=1");
};

export const getFinishedDonations = async (): Promise<Pagination> => {
  return getDonations("?status=4");
};

export const getOngoingDonations = async (): Promise<Pagination> => {
  return getDonations("?status=3");
};

export const getPendingDonations = async (): Promise<Pagination> => {
  return getDonations("?status=2");
};

const getDonations = async (status: string) => {
  try {
    const { data } = await axios.get<BackResponse>(donationsURL + status, {
      headers: {
        Accept: applicationJSON,
      },
    });

    return data.message;
  } catch (error: any) {
    throw new Error(error.response.data.errorMessage);
  }
};
