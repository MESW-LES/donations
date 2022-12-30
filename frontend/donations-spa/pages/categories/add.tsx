import axios from "axios";
import Router from "next/router";
import { Button } from "primereact/button";
import { Card } from "primereact/card";
import { InputText } from "primereact/inputtext";
import { InputTextarea } from "primereact/inputtextarea";
import { useState } from "react";
import { toast, ToastContainer } from "react-toastify";
import { createCategory } from "../../backend/Category";
import AppMenuBar from "../../components/AppMenuBar";
import { Category } from "../../types/Category";
import "react-toastify/dist/ReactToastify.css";

const AddCategory = () => {
  const [code, setCode] = useState("");
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");

  const handleSubmit = async () => {
    const category: Category = {
      code: code,
      name: name,
      description: description,
    };

    try {
      await createCategory(category);

      toast.success("The category was created with success!");

      // returns to categories list
      setTimeout(() => {
        Router.push("/categories");
      }, 2000);
    } catch (e: any) {
      toast.error(e.message);
    }
  };

  // goes back to the categories list
  const cancelAddCategory = () => {
    Router.push("/categories");
  };

  return (
    <>
      <AppMenuBar />
      <ToastContainer />
      <Card className="m-2">
        <h2 className="pb-4">Add Category</h2>
        <div className="pb-4">
          <label htmlFor="category_code">Category code</label>
          <InputText
            required
            minLength={1}
            maxLength={4}
            type="text"
            name="category_code"
            id="category_code"
            value={code}
            onChange={({ target }) => setCode(target?.value)}
            className="col-12"
          />
        </div>
        <div className="pb-4">
          <label htmlFor="category_name">Category name</label>
          <InputText
            required
            minLength={1}
            maxLength={50}
            type="text"
            name="category_name"
            id="category_name"
            value={name}
            onChange={({ target }) => setName(target?.value)}
            className="col-12"
          />
        </div>
        <div className="pb-4">
          <label htmlFor="category_description">Category Description</label>
          <InputTextarea
            required
            minLength={1}
            maxLength={280}
            name="category_description"
            id="category_description"
            value={description}
            onChange={({ target }) => setDescription(target?.value)}
            className="col-12"
          />
        </div>
        <div className="flex justify-center gap-4">
          <div>
            <Button
              label="Cancel"
              className="p-button-danger"
              onClick={cancelAddCategory}
            ></Button>
          </div>
          <div>
            <Button
              type="submit"
              onClick={handleSubmit}
              className="p-button-success"
              label="Save"
            ></Button>
          </div>
        </div>
      </Card>
    </>
  );
};

export default AddCategory;
