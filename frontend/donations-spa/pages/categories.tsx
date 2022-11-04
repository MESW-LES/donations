import axios from "axios";
import { useState } from "react";
import AppMenuBar from "./AppMenuBar";

function Categories() {
  const [code, setCode] = useState("");
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");

  const handleSubmit = async (event: any) => {
    event.preventDefault();

    const formValues = { code, name, description };

    //console.log(formValues);

    try {
      const { data } = await axios({
        url: "/api/form",
        method: "POST",
        data: formValues,
      });

      console.log("Response: ", data);
    } catch (error) {
      console.log("error:", error);
    }
  };
  return (
    <>
      <AppMenuBar />
      <div className="mt-10 sm:mt-0 pt-10">
        <div className="mt-5 md:col-span-2 md:mt-0 ">
          <form action="/api/form" method="POST">
            <div className="overflow-hidden shadow sm:rounded-md">
              <div className="bg-white px-4 py-5 sm:p-6">
                <div className="grid grid-cols-6 gap-6">
                  <div className="col-span-12">
                    <label
                      htmlFor="category_code"
                      className="block text-sm font-medium text-gray-700"
                    >
                      Category code
                    </label>
                    <input
                      required
                      minLength={1}
                      maxLength={4}
                      type="text"
                      name="category_code"
                      id="category_code"
                      value={code}
                      onChange={({ target }) => setCode(target?.value)}
                      //autoComplete="given-name"
                      className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>
                  <div className="col-span-12">
                    <label
                      htmlFor="category_name"
                      className="block text-sm font-medium text-gray-700"
                    >
                      Category name
                    </label>
                    <input
                      required
                      minLength={1}
                      maxLength={50}
                      type="text"
                      name="category_name"
                      id="category_name"
                      value={name}
                      onChange={({ target }) => setName(target?.value)}
                      //autoComplete="given-name"
                      className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>

                  <div className="col-span-12">
                    <label
                      htmlFor="category_description"
                      className="block text-sm font-medium text-gray-700"
                    >
                      Category Description
                    </label>
                    <input
                      required
                      minLength={1}
                      maxLength={280}
                      type="text"
                      name="category_description"
                      id="category_description"
                      value={description}
                      onChange={({ target }) => setDescription(target?.value)}
                      //autoComplete="family-name"
                      className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
                    />
                  </div>
                </div>
              </div>
              <div className="bg-gray-50 px-4 py-3 text-end sm:px-6">
                <button
                  type="button"
                  className="inline-flex justify-start rounded-md border border-transparent bg-red-600 py-2 px-4 text-sm font-medium text-white shadow-sm hover:bg-red-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  onClick={handleSubmit}
                  className="inline-flex justify-center rounded-md border border-transparent bg-green-600 py-2 px-4 text-sm font-medium text-white shadow-sm hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
                >
                  Save
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </>
  );
}

export default Categories;
