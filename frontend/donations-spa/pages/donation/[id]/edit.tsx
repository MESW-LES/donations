import axios from "axios";
import React, { useRef, useState, useEffect } from "react";
import AppMenuBar from "../../../components/AppMenuBar";
import { Toast } from "primereact/toast";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { FileUpload } from "primereact/fileupload";
import { ProgressBar } from "primereact/progressbar";
import { InputTextarea } from "primereact/inputtextarea";
import { Dropdown } from "primereact/dropdown";
import { Tag } from "primereact/tag";
import { Tooltip } from "primereact/tooltip";
import FormData from "form-data";
import { useRouter } from "next/router";
import { Splitter, SplitterPanel } from "primereact/splitter";
import { Galleria } from "primereact/galleria";

function AddDonation({ donation }: any) {
  // router constant for change the view
  const router = useRouter();

  //function to redirect to another view
  const goToPage = (page: string) => {
    router.push(page);
  };

  /***********file upload code**********/

  //buttons for image upload
  const chooseOptions = {
    icon: "pi pi-fw pi-images",
    iconOnly: true,
    className: "custom-choose-btn border-round w-1 p-button-outlined",
  };

  const cancelOptions = {
    icon: "pi pi-fw pi-times",
    iconOnly: true,
    className:
      "custom-cancel-btn p-button-danger border-round w-1 p-button-outlined",
  };

  //form information
  const [title, setTitle] = useState(donation.title);
  const [category, setCategory] = useState(donation.categories.code);
  const [description, setDescription] = useState(donation.description);
  const [images, setImages] = useState<any>(null);
  //for display messages in the view
  const toast = useRef<any>(null);
  //actual value for the dropdown of categories
  const [dropdownItem, setDropdownItem] = useState();
  //categories list from the server for the dropdown
  const [categoriesData, setCategoriesData] = useState<any>(null);

  /******************* Image Presentation ***********************/

  const fetchImages = () => {
    console.log(donation.donationImages);
    setImages(donation.donationImages);
  };

  const itemTemplate = (item: string) => {
    return (
      <img
        src={`http://localhost:8080/donationsImages/${item}.png`}
        onError={(e: any) =>
          (e.target.src =
            "https://www.primefaces.org/wp-content/uploads/2020/05/placeholder.png")
        }
        alt={item}
        style={{ width: "100%" }}
      />
    );
  };

  const thumbnailTemplate = (item: string) => {
    return (
      <img
        src={`http://localhost:8080/donationsImages/${item}.png`}
        onError={(e: any) =>
          (e.target.src =
            "https://www.primefaces.org/wp-content/uploads/2020/05/placeholder.png")
        }
        alt={item}
        style={{ width: "30%" }}
      />
    );
  };

  const responsiveOptions = [
    {
      breakpoint: "1024px",
      numVisible: 5,
    },
    {
      breakpoint: "768px",
      numVisible: 3,
    },
    {
      breakpoint: "560px",
      numVisible: 1,
    },
  ];

  /*****************************************/

  // POST to the server (Create donation)
  const handleSubmit = async (event: any) => {
    event.preventDefault();

    const dataDonation = new FormData();

    dataDonation.append("title", title);
    console.log(category);
    dataDonation.append("categoriesCode", category);
    dataDonation.append("description", description);
    
    try {
      const { data } = await axios({
        url: `http://localhost:8080/donations/${donation.id}`,        
        method: "PUT",
        data: dataDonation,
      });

      if (data.code != 200 && data.code != 201) {
        showToast(
          "error",
          "Hey",
          "oops looks like something went wrong, please try again later."
        );
      } else {
        showToast(
          "success",
          "Success Message",
          "The donation was updated successfully."
        );
        setTitle("");
        setCategory("");
        setDescription("");
        goToPage("/my-donations");
      }
    } catch (error) {
      if (error instanceof Error) {
        showToast("error", "Error " + error.message, error.message);
      }
    }
  };



//Fetch data from the BackEnd
const fetchCategories = async ()=>{
  const response = await fetch('/api/CategoryService');
  const data = await response.json();
  if(data.code = 200){
    setCategoriesData(data.data.message.results);
    if(data.data.message.results){
      //console.log(data.data.message.results)
      //console.log(donation)
      let obj = data.data.message.results.find((object : any) => object.code === donation.categories[0].code);
      setDropdownItem(obj);
      setCategory(obj.code);
//console.log(obj);
    }
  };

  // Get the categories
useEffect(() => {
  fetchCategories();
  fetchImages();
}, []); // eslint-disable-line react-hooks/exhaustive-deps

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
          <Toast ref={toast} />
          <div className="col-12">
            <div className="card">
              <h5>Edit donation</h5>
              <Splitter className="mb-5" layout="vertical">
          <SplitterPanel className="flex align-items-center justify-content-center">
          <div className="p-fluid formgrid grid">
                <div className="field col-12">
                  <label htmlFor="title">Title</label>
                  <InputText
                    id="title"
                    type="text"
                    value={title}
                    onChange={({ target }) => setTitle(target?.value)}
                  />
                </div>
                <div className="field col-12">
                  <label htmlFor="category">Category</label>
                  <Dropdown
                    id="category"
                    value={dropdownItem}
                    onChange={(e) => {
                      console.log(donation.categories);
                      console.log(e.value);
                      setDropdownItem(e.value);
                      setCategory(e.value.code);
                      console.log(category);
                    }}
                    options={categoriesData}
                    optionLabel="name"
                    placeholder="Select One"
                  ></Dropdown>
                </div>
                <div className="field col-12">
                  <label htmlFor="description">Description</label>
                  <InputTextarea id="description" rows= {4} value={description} onChange={({target})=> setDescription(target?.value)}/>
                </div>        
</div>
          </SplitterPanel>
          <SplitterPanel className="flex align-items-center justify-content-center col-12">
          <Galleria
              value={images}
              item={itemTemplate}
              thumbnail={thumbnailTemplate}            
              numVisible={5}
              responsiveOptions={responsiveOptions}
              style={{ maxWidth: "300px" }}
            ></Galleria>
          </SplitterPanel>
        </Splitter>
      
      <div className="flex align-items-center justify-content-end col-12">
      
      <button
                  type="submit"
                  onClick= {()=>goToPage("/my-donations")}
                  className=" px-4 py-2 rounded-md border border-transparent bg-red-600   font-medium text-white  hover:bg-red-700 "
                >
                  Cancel
                </button>
                
                
              <button
                  type="submit"
                  onClick={handleSubmit}
                  className="px-4 py-2 rounded-md border border-transparent bg-green-600  font-medium text-white  hover:bg-green-700 "
                >
                  Save
                </button>
                
                </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default AddDonation;

export async function getStaticPaths() {
  const requestOptions = {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  };
  const response = await fetch(
    `http://localhost:8080/donations`,
    requestOptions
  );
  const data = await response.json();
  console.log(data.message.results);

  const newPaths = data.message.results.map((post: any) => {
    return {
      params: {
        id: `${post.id}`,
      },
    };
  });
  return {
    paths: newPaths,
    fallback: false,
  };
}

export async function getStaticProps(context: any) {
  const { params } = context;
  console.log(params);
  const requestOptions = {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  };
  const response = await fetch(
    `http://localhost:8080/donations/${params.id}`,
    requestOptions
  );
  const data = await response.json();

  //console.log(data);
  return {
    props: {
      donation: data.message,
    },
  };
}
