import AppMenuBar from "./AppMenuBar";
import React, { useState, useEffect } from 'react';
import { DataView, DataViewLayoutOptions, DataViewLayoutType } from 'primereact/dataview';
import { Button } from 'primereact/button';
import { Dropdown } from 'primereact/dropdown';
import { Rating } from 'primereact/rating';
import { ProductService } from './api/ProductService';
import { InputText } from 'primereact/inputtext';
import { useRouter } from "next/router";

function HomeContent() {  

const [dataViewValue, setDataViewValue] = useState<any>(null);
const [globalFilterValue, setGlobalFilterValue] = useState('');
const [filteredValue, setFilteredValue] = useState(null); 
const [layout, setLayout] = useState<DataViewLayoutType>('grid');
const [sortKey, setSortKey] = useState(null);
const [sortOrder, setSortOrder] = useState(null);
const [sortField, setSortField] = useState<any>(null);
//actual value for the dropdown of categories
const [dropdownItem, setDropdownItem] = useState(null);
//categories list from the server for the dropdown
const [categoriesData, setCategoriesData] = useState<any>(null);
//const contextPath = getConfig().publicRuntimeConfig.contextPath;

const router = useRouter();
const goToPage = (page: string) => {
    router.push(page);
  };

//posible options to sort
const sortOptions = [
    /* { label: 'Price High to Low', value: '!price' },
    { label: 'Price Low to High', value: 'price' } */
    { label: 'Best user rating', value: 'userRating' },
    { label: 'Most recent', value: 'publishDate' }
];

//Fetch data from the BackEnd
const fetchCategories = async ()=>{
    const response = await fetch('/api/CategoryService');
    const data = await response.json();
    if(data.code = 200){
    data.data.message.results.push({'code':'ALL', 'name':'ALL ITEMS','description':'all categories'});
      setCategoriesData(data.data.message.results);
    }
    
  }

 useEffect(() => {
    /* const productService = new ProductService();
    productService.getProducts().then((data) => setDataViewValue(data));
     */
    fetchActiveDonations();
    setGlobalFilterValue('');
    fetchCategories();
}, []); 



//Fetch data from the BackEnd
const fetchActiveDonations = async ()=>{
    const requestOptions = {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' }
  };
   const response = await fetch('http://localhost:8080/donations?status=1', requestOptions);
   const data = await response.json();
   if(data.code == 200){
    if(data.message){
      setDataViewValue(data.message.results);
    }
    //console.log(dataViewValue[0].donationImages[0])
}        
  }

  //Fetch data from the BackEnd
const fetchCategoryDonations = async (code:any)=>{
    const requestOptions = {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' }
  };
   const response = await fetch(`http://localhost:8080/donations?status=1&category=${code}`, requestOptions);
   const data = await response.json();
   if(data.code == 200){
    if(data.message){
      setDataViewValue(data.message.results);
    }
    //console.log(dataViewValue[0].donationImages[0])
}        
  }
  

// Filter option
const onFilter = (e : any) => {
    const value = e.target.value;
    setGlobalFilterValue(value);
    if (value.length === 0) {
        setFilteredValue(null);
    }
    else {
        const filtered = dataViewValue.filter((product: any) => {
            return product.title.toLowerCase().includes(value);
        });
        setFilteredValue(filtered);
    }
};

//Sort option, define value to sort
const onSortChange = (event : any) => {
    const value = event.value;

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

const onCategoryChange = (e : any)=>{
    console.log(e);
    if(e!= undefined){
        console.log(e.code);
        if(e.code === "ALL"){
            fetchActiveDonations();
        }
        fetchCategoryDonations(e.code);
    }
}

const dataViewHeader = (
    <div className="flex flex-column md:flex-row md:justify-content-between gap-2">
        <Dropdown value={sortKey} options={sortOptions} optionLabel="label" placeholder="Sort By User Rating" onChange={onSortChange} />
        <Dropdown value={dropdownItem} options={categoriesData} optionLabel="name" placeholder="Search by category" onChange={(e)=>{setDropdownItem(e.value);                                    
                    onCategoryChange(e.value);             
                    }} />
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
                {/* <img src={`demo/images/product/${data.image}`} alt={data.name} className="my-4 md:my-0 w-9 md:w-10rem shadow-2 mr-5" /> */}
                <img src={`http://localhost:8080/donationsImages/${data.donationImages[0]}.png`} alt={data.title} className="my-4 md:my-0 w-9 md:w-10rem shadow-2 mr-5" />
                <div className="flex-1 flex flex-column align-items-center text-center md:text-left">
                    <div className="font-bold text-2xl">{data.title}</div>
                    <div className="mb-2">{data.description}</div>
                    <Rating value={data.rating} readOnly cancel={false} className="mb-2"></Rating>
                    <div className="flex align-items-center">
                        <i className="pi pi-tag mr-2"></i>
                        <span className="font-semibold">{data.categories[0].name.toUpperCase()}</span>
                    </div>
                </div>
                <div className="flex flex-row md:flex-column justify-content-between w-full md:w-auto align-items-center md:align-items-end mt-5 md:mt-0">
                <div>
                <i className="pi pi-calendar mr-2" />
                    <span className="font-semibold mb-2 align-self-center md:align-self-end">{data.createdDate}</span>
                    </div>
                    <Button icon="pi pi-shopping-cart" label="Donation process" disabled={data.donationProcess.status.toUpperCase() === 'END'} className="mb-2 p-button-sm" onClick={() => goToPage(`donation/${data.id}`)}></Button>
                
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
                
                </div>
                <div className="flex flex-column align-items-center text-center mb-3">
                    <img src={`http://localhost:8080/donationsImages/${data.donationImages[0]}.png`} alt={data.title} className="h-40 w-8 shadow-2 my-3 mx-0" />
                    <div className="text-2xl font-bold">{data.title}</div>
                    <div className="mb-3">{data.description}</div>
                    <Rating value={data.rating} readOnly cancel={false} />
                </div>
                <div className="flex align-items-center justify-content-between">
                    <div>
                <i className="pi pi-calendar mr-2" />
                    <span className="font-semibold">{data.createdDate.substring(0,10)}</span>
                    </div>
                    <Button icon="pi pi-shopping-cart" label="Donation process" disabled={data.donationProcess.status.toUpperCase() === 'END'} onClick={() => goToPage(`donation/${data.id}`)} />
                </div>
            </div>
        </div>
    );
};

const itemTemplate = (data : any, layout:any) => {
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
  <h1>Hello, <b>UserName</b></h1>
    <div className="grid list-demo">
        <div className="col-12">
            <div className="card">
                <h5>Inspired by your interests</h5>
                <DataView value={filteredValue || dataViewValue} layout={layout} paginator rows={3} sortOrder={sortOrder} sortField={sortField} itemTemplate={itemTemplate} header={dataViewHeader}></DataView>
            </div>
            <div className="card">
                <h5>Donations history</h5>
                <DataView value={filteredValue || dataViewValue} layout={layout} paginator rows={3} sortOrder={sortOrder} sortField={sortField} itemTemplate={itemTemplate} header={dataViewHeader}></DataView>
            </div>
        </div>
    </div>
    </>
);
}

export default HomeContent;