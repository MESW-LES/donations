import axios from "axios";
import React ,{ useRef, useState, useEffect } from "react";
import AppMenuBar from "./AppMenuBar";
import { Toast } from 'primereact/toast';
import { InputText } from "primereact/inputtext";
import { InputTextarea } from "primereact/inputtextarea";
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { CategoryService } from './api/CategoryService';
import { Dropdown } from 'primereact/dropdown';

function Categories() {

  const [categoriesData, setCategoriesData] = useState<any>(null);

  // HARD CODE for categories
  
  const [categoriesLenght, setCategoriesLenght] = useState<number>(0);

  // Update the new Data
  const onRowEditComplete1 = (e : any) => {
    let _categoriesData = [...categoriesData];
    //console.log(e);
    let { newData, index } = e;
    _categoriesData[index] = newData;

    //TODO add update to the db


    //updates the list of categories
    setCategoriesData(_categoriesData);
}

//Fetch data from the BackEnd
const fetchCategories = async ()=>{
  const response = await fetch('/api/CategoryService');
  const data = await response.json();
  console.log(data.data.message.results);
  console.log(data.code);
  if(data.code = 200){
    setCategoriesLenght(data.data.message.countResults);
    setCategoriesData(data.data.message.results);
  }
  
}

// Get the categories
useEffect(() => {
  fetchCategories();
}, []); // eslint-disable-line react-hooks/exhaustive-deps


const statuses = [
  { label: 'Active', value: 'true' },
  { label: 'Inactive', value: 'false' }
];

//get status label for the list
const getStatusLabel = (status : String) => {
  switch (status) {
      case 'true':
          return 'Active';

      case 'false':
          return 'Inactive';

      default:
          return 'NA';
  }
}

// for the state of a categorie (active/inactive)
const statusBodyTemplate = (rowData : any) => {
  return getStatusLabel(rowData.inventoryStatus);
}
const textEditor = (options:any) => {
  return <InputText type="text" value={options.value} onChange={(e) => options.editorCallback(e.target.value)} />;
}

const statusEditor = (options:any) => {
  return (
      <Dropdown value={options.value} options={statuses} optionLabel="label" optionValue="value"
          onChange={(e) => options.editorCallback(e.value)} placeholder="Select a Status"
          itemTemplate={(option) => {
              return <span className={`product-badge status-${option.value.toLowerCase()}`}>{option.label}</span>
          }} />
  );
}
  // *************************
  
  
  
  const myToast = useRef<any>(null);
  
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
      if(error instanceof Error){
        showToast('error','Error '+ error.message, error.message);
      }
      
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
          <form action="/api/form" method="POST">
            <div className="overflow-auto sm:rounded-md">
              <div className="card px-6 py-5 ">                
                  <div className="field col-12">
                    <label
                      htmlFor="category_code"                
                    >
                      Category code
                    </label>
                    <InputText
                      required
                      minLength={1}
                      maxLength={4}
                      type="text"
                      name="category_code"
                      id="category_code"
                      value={code}
                      onChange={({ target }) => setCode(target?.value)}
                      //autoComplete="given-name"     
                      className="col-12"           
                    />
                  </div>
                  <div className="field col-12">
                    <label
                      htmlFor="category_name"                    
                    >
                      Category name
                    </label>
                    <InputText
                      required
                      minLength={1}
                      maxLength={50}
                      type="text"
                      name="category_name"
                      id="category_name"
                      value={name}
                      onChange={({ target }) => setName(target?.value)}
                      //autoComplete="given-name"
                      className="col-12"
                    />
                  </div>

                  <div className="field col-12">
                    <label
                      htmlFor="category_description"
            
                    >
                      Category Description
                    </label>
                    <InputTextarea
                      required
                      minLength={1}
                      maxLength={280}      
                      name="category_description"
                      id="category_description"
                      value={description}
                      onChange={({ target }) => setDescription(target?.value)}
                      //autoComplete="family-name"
                      className="col-12"
                    />
                  </div>
                </div>
              </div>
              <div className="card px-4 py-3 text-end sm:px-6">
                <button
                  type="button"
                  onClick={()=>{setName("");
                  setCode("");
                  setDescription("");}}
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
          </form>        
        </div>
        <div className="col-12">
        <div className="card p-fluid">
                <h5>Categories List</h5>
                <DataTable value={categoriesData} editMode="row" dataKey="id" onRowEditComplete={onRowEditComplete1} responsiveLayout="scroll">
                    <Column field="code" header="Code" editor={(options) => textEditor(options)} style={{ width: '20%' }}></Column>
                    <Column field="name" header="Name" editor={(options) => textEditor(options)} style={{ width: '20%' }}></Column>
                    <Column field="ACTIVE" header="Status" body={statusBodyTemplate} editor={(options) => statusEditor(options)} style={{ width: '20%' }}></Column>                    
                    <Column rowEditor headerStyle={{ width: '10%', minWidth: '8rem' }} bodyStyle={{ textAlign: 'center' }}></Column>
                </DataTable>
            </div>
      </div>
      </div>
    </>
  );
}

export default Categories;
