import axios from "axios";
import React, { useRef, useState , useEffect} from "react";
import AppMenuBar from "./AppMenuBar";
import { Toast } from "primereact/toast";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { FileUpload } from "primereact/fileupload";
import { ProgressBar } from "primereact/progressbar";
import { InputTextarea } from "primereact/inputtextarea";
import { Dropdown } from "primereact/dropdown";
import { Tag } from "primereact/tag";
import { Tooltip } from "primereact/tooltip";
//import { Exception } from "sass";

function AddDonation() {
  /***********file upload code**********/
  const chooseOptions = {
    icon: "pi pi-fw pi-images",
    iconOnly: true,
    className: "custom-choose-btn border-round w-1 p-button-outlined",
  };
  const uploadOptions = {
    icon: "pi pi-fw pi-cloud-upload",
    iconOnly: true,
    className:
      "custom-upload-btn p-button-success border-round w-1 p-button-outlined",
  };
  const cancelOptions = {
    icon: "pi pi-fw pi-times",
    iconOnly: true,
    className:
      "custom-cancel-btn p-button-danger border-round w-1 p-button-outlined",
  };

  const [title, setTitle] = useState("");
  const [category, setCategory] = useState("");
  const [description, setDescription] = useState("");
  const [totalSize, setTotalSize] = useState(0);
  const toast = useRef<any>(null);
  const fileUploadRef = useRef<any>(null);
  const [dropdownItem, setDropdownItem] = useState(null);
  //const [dropdownItems, setDropdownItems] = useState<any>(null);
  const [categoriesData, setCategoriesData] = useState<any>(null);


  const myToast = useRef(null);

//Fetch data from the BackEnd
const fetchCategories = async ()=>{
  const response = await fetch('/api/CategoryService');
  const data = await response.json();
  console.log(data.data.message.results);
  //console.log(data.code);
  if(data.code = 200){
    setCategoriesData(data.data.message.results);
  }
  
}

  // Get the categories
useEffect(() => {
  fetchCategories();
}, []); // eslint-disable-line react-hooks/exhaustive-deps


  const onUpload = () => {
    if(toast.current !== null && toast.current !== undefined ){
      toast.current.show({
        severity: "info",
        summary: "Success",
        detail: "File Uploaded",
      });
    }
  };

  const onTemplateSelect = (e: any) => {
    let _totalSize = totalSize;
    Array.from(e.files).forEach((file : any) => {
      _totalSize += file.size;
    });

    setTotalSize(_totalSize);
  };

  const onTemplateUpload = (e : any) => {
    let _totalSize = 0;
    e.files.forEach((file:any) => {
      _totalSize += file.size || 0;
    });

    setTotalSize(_totalSize);
    toast.current.show({
      severity: "info",
      summary: "Success",
      detail: "File Uploaded",
    });
  };

  const onTemplateRemove = (file : any, callback : any) => {
    setTotalSize(totalSize - file.size);
    if(callback){
      callback();
    }
  };

  const onTemplateClear = () => {
    setTotalSize(0);
  };

  const headerTemplate = (options: any) => {
    const { className, chooseButton, uploadButton, cancelButton } = options;
    const value = totalSize / 10000;
    const formatedValue =
      fileUploadRef && fileUploadRef.current
        ? fileUploadRef.current.formatSize(totalSize)
        : "0 B";

    return (
      <div
        className={className}
        style={{
          backgroundColor: "transparent",
          display: "flex",
          alignItems: "center",
        }}
      >
        {chooseButton}
        {uploadButton}
        {cancelButton}
        <ProgressBar
          value={value}
          displayValueTemplate={() => `${formatedValue} / 1 MB`}
          style={{ width: "300px", height: "20px", marginLeft: "auto" }}
        ></ProgressBar>
      </div>
    );
  };

  const itemTemplate = (file:any, props:any) => {
    return (
      <div className="flex align-items-center flex-wrap">
        <div className="flex align-items-center" style={{ width: "40%" }}>
          <img
            alt={file.name}
            role="presentation"
            src={file.objectURL}
            width={150}
          />
          <span className="flex flex-column text-left ml-3">
            {file.name}
            <small>{new Date().toLocaleDateString()}</small>
          </span>
        </div>
        <Tag
          value={props.formatSize}
          severity="warning"
          className="px-3 py-2"
        />
        <Button
          type="button"
          icon="pi pi-times"
          className="p-button-outlined p-button-rounded p-button-danger ml-auto"
          onClick={() => onTemplateRemove(file, props.onRemove)}
        />
      </div>
    );
  };

  const emptyTemplate = () => {
    return (
      <div className="flex align-items-center flex-column">
        <i
          className="pi pi-image mt-3 p-5"
          style={{
            fontSize: "5em",
            borderRadius: "50%",
            backgroundColor: "var(--surface-b)",
            color: "var(--surface-d)",
          }}
        ></i>
        <span
          style={{ fontSize: "1.2em", color: "var(--text-color-secondary)" }}
          className="my-5"
        >
          Drag and Drop Image Here
        </span>
      </div>
    );
  };

  /*****************************/



  const handleSubmit = async (event: any) => {
    event.preventDefault();

    const formValues = { title, category, description };

    //console.log(formValues);

    try {
      const { data } = await axios({
        url: "/api/donations",
        method: "POST",
        data: formValues,
      });

      if (data.code != 200) {
        showToast(
          "error",
          "Hey",
          "oops looks like something went wrong, please try again later."
        );
      } else {
        showToast(
          "success",
          "Success Message",
          "The category was added successfully."
        );
        setTitle("");
        setCategory("");
        setDescription("");
      }
    } catch (error) {
      if(error instanceof Error){
        showToast(
          "error",
          "Error " + error.message,
          error.message
        );
      }
      
    }
  };

  const showToast = (
    severityValue: string,
    summaryValue: string,
    detailValue: string
  ) => {
    toast.current.show({
      severity: severityValue,
      summary: summaryValue,
      detail: detailValue,
    });
  };

  // AppMenuBar display the menu with the logo
  // Tooltip is a brief, informative message that appears when a user interacts with an element.
  // Toast is for message display
  return (
    <>
      <AppMenuBar />
      <div className="pt-10">
        <Tooltip
          target=".custom-choose-btn"
          content="Choose"
          position="bottom"
        />
        <Tooltip
          target=".custom-cancel-btn"
          content="Clear"
          position="bottom"
        />

        <div className="md:col-12">
          <Toast ref={myToast} />
          <div className="col-12">
            <div className="card">
              <h5>New donation</h5>
              <div className="p-fluid formgrid grid">
                <div className="field col-12">
                  <label htmlFor="title">Title</label>
                  <InputText id="title" type="text" value={title}
                      onChange={({ target }) => setTitle(target?.value)}/>
                </div>
                <div className="field col-12">
                  <label htmlFor="category">Category</label>
                  <Dropdown
                    id="category"
                    value={dropdownItem}
                    onChange={(e)=>{setDropdownItem(e.value);
                    setCategory(e.value.code);                
                    }}
                    options={categoriesData}
                    optionLabel="name"
                    placeholder="Select One"
                  ></Dropdown>
                </div>
                <div className="field col-12">
                  <label htmlFor="description">Description</label>
                  <InputTextarea id="description" rows= {4} />
                </div>        
                <FileUpload
                  ref={fileUploadRef}
                  name="demo[]"
                  url="https://primefaces.org/primereact/showcase/upload.php"
                  multiple
                  accept="image/*"
                  maxFileSize={1000000}
                  onUpload={onTemplateUpload}
                  onSelect={onTemplateSelect}
                  onError={onTemplateClear}
                  onClear={onTemplateClear}
                  headerTemplate={headerTemplate}
                  itemTemplate={itemTemplate}
                  emptyTemplate={emptyTemplate}
                  chooseOptions={chooseOptions}
                  uploadOptions={uploadOptions}
                  cancelOptions={cancelOptions}
                  className = "field col-12"
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default AddDonation;
