import axios from "axios";
import React ,{ useRef, useState } from "react";
import AppMenuBar from "./AppMenuBar";
import { Toast } from 'primereact/toast';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { FileUpload } from 'primereact/fileupload';
import { ProgressBar } from 'primereact/progressbar';
import { InputTextarea } from 'primereact/inputtextarea';
import { Dropdown } from 'primereact/dropdown';

function AddDonation() {

    const [dropdownItem, setDropdownItem] = useState(null);
    const dropdownItems = [
        { name: 'Electronics', code: 'ELT' },
        { name: 'Phones', code: 'PHN' }
    ];

  const myToast = useRef(null);
  
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

      if(data.code != 200 ){
        showToast('error',"Hey",'oops looks like something went wrong, please try again later.')
      }else{
        showToast('success','Success Message','The category was added successfully.');
        setName("");
        setCode("");
        setDescription("");
      };      
    } catch (error) {
      showToast('error','Error '+ error.response.status, error.response.data.data);
      //showToast('error','Error '+ error.response.status,'oops looks like something went wrong, please try again later.');    
    }
  };

  const showToast = (severityValue: string, summaryValue: string, detailValue: string) => {   
    myToast.current.show({severity: severityValue, summary: summaryValue, detail: detailValue});   
  };

  return (
    <>
      <AppMenuBar />
      <div className="pt-10">
        <div className="md:col-12">
        <Toast ref={myToast} /> 
        <div className="col-12">
                <div className="card">
                    <h5>New donation</h5>
                    <div className="p-fluid formgrid grid">
                        <div className="field col-12">
                            <label htmlFor="productname">Product name</label>
                            <InputText id="productname" type="text" />
                        </div>
                        <div className="field col-12">
                            <label htmlFor="category">Category</label>
                            <Dropdown id="category" value={dropdownItem} onChange={(e) => setDropdownItem(e.value)} options={dropdownItems} optionLabel="name" placeholder="Select One"></Dropdown>
                        </div>                
                        <div className="field col-12">
                            <label htmlFor="description">Description</label>
                            <InputTextarea id="description" rows="4" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
      </div>
    </>
  );
}

export default AddDonation;
