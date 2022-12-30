import React, { useRef, useState, useEffect } from "react";
import AppMenuBar from "../components/AppMenuBar";
import { InputText } from "primereact/inputtext";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Dropdown } from "primereact/dropdown";
import { Card } from "primereact/card";
import { Category } from "../types/Category";
import { getCategories, updateCategory } from "../backend/Category";
import { Pagination } from "../types/Pagination";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Button } from "primereact/button";
import Router from "next/router";

function Categories() {
  const [categoriesData, setCategoriesData] = useState<Category[]>();

  const [categoriesLenght, setCategoriesLenght] = useState<number>(0);

  // Update the new Data
  const onRowEditComplete = async (e: any) => {
    const _categoriesData: Category[] =
      categoriesData === undefined ? [] : [...categoriesData];
    const { newData, index } = e;
    const updatedCategory: Category = newData;

    // updates the category in backend
    try {
      await updateCategory(updatedCategory.code, updatedCategory);

      _categoriesData[index] = updatedCategory;

      //updates the list of categories
      setCategoriesData(_categoriesData);

      toast.info("The category was successfully updated!");
    } catch (e: any) {
      toast.error(e.message);
    }
  };

  //Fetch data from the BackEnd
  const fetchCategories = async () => {
    const pagination: Pagination = await getCategories();
    setCategoriesData(pagination.results);
    setCategoriesLenght(pagination.countResults);
  };

  // Get the categories
  useEffect(() => {
    fetchCategories();
  }, []); // eslint-disable-line react-hooks/exhaustive-deps

  const statuses = [
    { label: "Active", value: true },
    { label: "Inactive", value: false },
  ];

  // for the state of a categorie (active/inactive)
  const statusBodyTemplate = (category: any) => {
    return category.active ? <div>Active</div> : <div>Inactive</div>;
  };

  const textEditor = (options: any) => {
    return (
      <InputText
        type="text"
        className="w-fit"
        value={options.value}
        onChange={(e) => options.editorCallback(e.target.value)}
      />
    );
  };

  const statusEditor = (options: any) => {
    return (
      <Dropdown
        value={options.value}
        options={statuses}
        optionLabel="label"
        optionValue="value"
        onChange={(e) => options.editorCallback(e.value)}
        placeholder="Select a Status"
        itemTemplate={(option) => {
          return <div>{option.label}</div>;
        }}
      />
    );
  };

  const onClickAddCategory = () => {
    Router.push("/categories/add");
  };

  return (
    <>
      <AppMenuBar />
      <ToastContainer />
      <div className="col-12">
        <Card>
          <div className="flex justify-between">
            <div>
              <h2>Categories</h2>
            </div>
            <div>
              <Button
                className="p-button-info"
                label="Add category"
                icon="pi pi-plus"
                onClick={onClickAddCategory}
              />
            </div>
          </div>
          <DataTable
            value={categoriesData}
            editMode="row"
            dataKey="id"
            onRowEditComplete={onRowEditComplete}
            responsiveLayout="scroll"
          >
            <Column
              field="code"
              header="Code"
              editor={(options) => textEditor(options)}
              style={{ width: "20%" }}
            ></Column>
            <Column
              field="name"
              header="Name"
              editor={(options) => textEditor(options)}
              style={{ width: "20%" }}
            ></Column>
            <Column
              field="description"
              header="Description"
              editor={(options) => textEditor(options)}
              style={{ width: "40%" }}
            ></Column>
            <Column
              field="active"
              header="Status"
              body={statusBodyTemplate}
              editor={(options) => statusEditor(options)}
              style={{ width: "10%" }}
            ></Column>
            <Column
              rowEditor
              headerStyle={{ width: "10%", minWidth: "8rem" }}
              bodyStyle={{ textAlign: "center" }}
            ></Column>
          </DataTable>
        </Card>
      </div>
    </>
  );
}

export default Categories;
