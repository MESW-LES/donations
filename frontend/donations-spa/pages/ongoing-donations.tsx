import AppMenuBar from "./AppMenuBar";
import React, { useState, useEffect, useRef } from 'react';
import { DataView, DataViewLayoutOptions, DataViewLayoutType } from 'primereact/dataview';
import { Button } from 'primereact/button';
import { Dropdown } from 'primereact/dropdown';
import { ProductService } from './api/ProductService';
import { InputText } from 'primereact/inputtext';
import { useRouter } from "next/router";
import axios from "axios";
import { Toast } from "primereact/toast";

function OngoingDonations() {

// Donations items
const [dataViewValue, setDataViewValue] = useState<any>(null);
// String to search on the donation items
const [globalFilterValue, setGlobalFilterValue] = useState('');
// Donations items filtered?
const [filteredValue, setFilteredValue] = useState(null); 
// If is a grid or a list
const [layout, setLayout] = useState<DataViewLayoutType>('grid');
// Option that is selected to sort
const [sortKey, setSortKey] = useState(null);
//for display messages in the view
const toast = useRef<any>(null);
const [sortOrder, setSortOrder] = useState<any>(null);
const [sortField, setSortField] = useState<any>(null);


const router = useRouter();

const [search, setSearch] = useState("");

const goToPage = (page: string) => {
  router.push(page);
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

//posible options to sort
const sortOptions = [
    /* { label: 'Price High to Low', value: '!price' },
    { label: 'Price Low to High', value: 'price' } */
    //{ label: 'Best user rating', value: 'userRating' },
    { label: 'Most recent', value: 'publishDate' },
    { label: 'Oldest', value: 'publishDateOld' },
    { label: 'Active', value: 'activeStatus' },
    { label: 'Delivered', value: 'deliveredStatus' }
];

//Fetch data from the BackEnd
const fetchOngoingDonations = async ()=>{
    const requestOptions = {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' }
  };
   const response = await fetch('http://localhost:8080/donations?status=3', requestOptions);
   const data = await response.json();
   if(data.code == 200){
    if(data.message){
        console.log(data.message)
      setDataViewValue(data.message.results);
    }
    //console.log(dataViewValue[0].donationImages[0])
}
    
    
  }

  //Fetch delete donation BackEnd
/* const deleteDonation = async (id: any)=>{
    try {
        const { data } = await axios({
          url: `http://localhost:8080/donations/${id}`,        
          method: "DELETE",
        });
  
        if (data.code != 200 && data.code != 201 ) {
          showToast(
            "error",
            "Hey",
            "oops looks like something went wrong, please try again later."
          );
        } else {
          showToast(
            "success",
            "Success Message",
            "The donation was deleted successfully."
          );
          fetchDonations();
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
  } */

 useEffect(() => {
    /* const productService = new ProductService();
    productService.getProducts().then((data) => setDataViewValue(data)); */
    fetchOngoingDonations();
    setGlobalFilterValue('');
}, []); 

// Filter option (Search)
const onFilter = (e : any) => {
    const value = e.target.value;
    setGlobalFilterValue(value);
    if (value.length === 0) {
        setFilteredValue(null);
    }
    else {
        const filtered = dataViewValue.filter((product:any) => {
                return product.title.toLowerCase().includes(value);
        });
        setFilteredValue(filtered);
    }
};

//Sort option, define value to sort
const onSortChange = (event : any) => {
    const value = event.value;
console.log(value === "publishDate");
if(value === "publishDate"){
    setSortOrder(-1);
    setSortField("publish_date");
    setSortKey(value);
}
if(value === "publishDateOld"){
    setSortOrder(1);
    setSortField("publish_date");
    setSortKey(value);
}
if(value === "activeStatus"){
    setSortOrder(1);
    setSortKey(value);
    setSortField("category");
}
if(value === "deliveredStatus"){
    setSortOrder(-1);
    setSortKey(value);
    setSortField("status");
}
   /*  if (value.indexOf('!') === 0) {
        setSortOrder(-1);
        setSortField(value.substring(1, value.length));
        setSortKey(value);
    } else {
        setSortOrder(1);
        setSortField(value);
        setSortKey(value);
    } */
};

const dataViewHeader = (
    <div className="flex flex-column md:flex-row md:justify-content-between gap-2">
        <Dropdown value={sortKey} options={sortOptions} optionLabel="label" placeholder="Sort Options" onChange={onSortChange} />
        <span className="p-input-icon-left">
            <i className="pi pi-search" />
            <InputText value={globalFilterValue} onChange={onFilter} placeholder="Search by Name" />
        </span>
        <DataViewLayoutOptions layout={layout} onChange={(e) => setLayout(e.value)} />
    </div>
);

const dataviewListItem = (data: any) => {
    return (
        <div className="col-12">
            <div className="flex flex-column md:flex-row align-items-center p-3 w-full">                
                <img src={`http://localhost:8080/donationsImages/${data.donationImages[0]}.png`} alt={data.title} className="my-4 md:my-0 w-9 md:w-10rem shadow-2 mr-5" />
                <div className="flex-1 flex flex-column align-items-center text-center md:text-left">
                    <div className="font-bold text-2xl">{data.title}</div>
                    <div className="mb-2">{data.description}</div>            
                    <div className="flex align-items-center">
                        <i className="pi pi-tag mr-2"></i>
                        <span className="font-semibold">{data.categories[0].name.toUpperCase()}</span>
                    </div>
                </div>
                <div className="flex flex-row md:flex-column justify-content-between w-full md:w-auto align-items-center md:align-items-end mt-5 md:mt-0">
                    <span className="text-2xl font-semibold mb-2 align-self-center md:align-self-end">{data.createdDate}</span>                    
                    <Button onClick={() => goToPage("donation/"+data.id)} icon="pi pi-pencil" label="Edit donation" disabled={data.donationProcess.status.toUpperCase() === 'END'} className="mb-2 p-button-sm"></Button>
                    <span className={`product-badge status-${data.donationProcess.status.toLowerCase()}`}>{data.donationProcess.status}</span>
                </div>
            </div>
        </div>
    );
};

const dataviewGridItem = (data: any) => {
    return (
        <div className="col-12 lg:col-4">
            <div className="card m-3 border-1 surface-border">
                <div className="flex flex-wrap gap-2 align-items-center justify-content-between mb-2">
                    <div className="flex align-items-center">
                        <i className="pi pi-tag mr-2" />
                        <span className="font-semibold">{data.categories[0].name.toUpperCase()}</span>
                    </div>
                    <span className={`product-badge status-${data.donationProcess.status.toLowerCase()}`}>{data.donationProcess.status}</span>
                   
                </div>
                <div className="flex flex-column align-items-center text-center mb-3">
                    <img src={`http://localhost:8080/donationsImages/${data.donationImages[0]}.png`} alt={data.title} className="h-40 w-8 shadow-2 my-3 mx-0" />
                    <div className="text-2xl font-bold">{data.title}</div>
                    <div className="mb-3">{data.description}</div>                    
                </div>
                <div className="flex align-items-center justify-content-between">
                    <span className="text-2xl font-semibold">{data.createdDate.substring(0,10)}</span>
                    <div>                  
                    <Button onClick={() => goToPage("donation/"+data.id)} icon="pi pi-pencil" disabled={data.donationProcess.status.toUpperCase() === 'END'} />                
                    </div>
                </div>
            </div>
        </div>
    );
};

const itemTemplate = (data: any, layout: DataViewLayoutType) => {
    if (!data) {
        return;        
    }

    if (layout === 'list') {        
        return dataviewListItem(data);
    } else if (layout === 'grid') {
        return dataviewGridItem(data);
    }
};

return (
  <>
  <AppMenuBar />
  <div className="col-12 flex flex-wrap justify-content-between">  
  </div>
  <Toast ref={toast} />
    <div className="grid list-demo">
        <div className="col-12">
            <div className="card">
                <h5>Ongoing donations</h5>
                <DataView value={filteredValue || dataViewValue} layout={layout} paginator rows={12} sortOrder={sortOrder} sortField={sortField} itemTemplate={itemTemplate} header={dataViewHeader}></DataView>
            </div>
        </div>
    </div>
    </>
);
}

export default OngoingDonations;

