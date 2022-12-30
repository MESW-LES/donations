import axios from "axios";
import { BackResponse } from "../types/BackResponse";
import { Category } from "../types/Category";
import { Pagination } from "../types/Pagination";

const categoriesURL: string = "http://localhost:8080/categories";
const applicationJSON: string = "application/json";

export const getCategories = async (): Promise<Pagination> => {
  try {
    const { data } = await axios.get<BackResponse>(categoriesURL, {
      headers: {
        Accept: applicationJSON,
      },
    });

    return data.message;
  } catch (error: any) {
    throw new Error(error.response.data.errorMessage);
  }
};

export const updateCategory = async (
  categoryCode: string,
  category: Category
): Promise<Category> => {
  try {
    const { data } = await axios.put<BackResponse>(
      categoriesURL + "/" + categoryCode,
      category,
      {
        headers: {
          Accept: applicationJSON,
        },
      }
    );
    return data.message;
  } catch (error: any) {
    throw new Error(error.response.data.errorMessage);
  }
};

export const createCategory = async (category: Category): Promise<Category> => {
  try {
    const { data } = await axios.post<BackResponse>(categoriesURL, category, {
      headers: {
        Accept: applicationJSON,
      },
    });
    return data.message;
  } catch (error: any) {
    throw new Error(error.response.data.errorMessage);
  }
};
