import AppMenuBar from "./AppMenuBar";
import React, { useState, useEffect } from 'react';
import { DataView, DataViewLayoutOptions } from 'primereact/dataview';
import { Button } from 'primereact/button';
import { Dropdown } from 'primereact/dropdown';
import { ProductService } from './api/ProductService';
import { InputText } from 'primereact/inputtext';

function MyDonations() {
  
// Donations items
const [dataViewValue, setDataViewValue] = useState(null);
// String to search on the donation items
const [globalFilterValue, setGlobalFilterValue] = useState('');
// Donations items filtered?
const [filteredValue, setFilteredValue] = useState(null); 
// If is a grid or a list
const [layout, setLayout] = useState('grid');
// Option that is selected to sort
const [sortKey, setSortKey] = useState(null);
const [sortOrder, setSortOrder] = useState(null);
const [sortField, setSortField] = useState(null);

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

 useEffect(() => {
    const productService = new ProductService();
    productService.getProducts().then((data) => setDataViewValue(data));
    setGlobalFilterValue('');
}, []); 

// Filter option (Search)
const onFilter = (e: any) => {
    const value = e.target.value;
    setGlobalFilterValue(value);
    if (value.length === 0) {
        setFilteredValue(null);
    }
    else {
        const filtered = dataViewValue.filter((product) => {
                return product.name.toLowerCase().includes(value);
        });
        setFilteredValue(filtered);
    }
};

//Sort option, define value to sort
const onSortChange = (event) => {
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

const dataviewListItem = (data) => {
    return (
        <div className="col-12">
            <div className="flex flex-column md:flex-row align-items-center p-3 w-full">                
                <img src={`images/product/${data.image}`} alt={data.name} className="my-4 md:my-0 w-9 md:w-10rem shadow-2 mr-5" />
                <div className="flex-1 flex flex-column align-items-center text-center md:text-left">
                    <div className="font-bold text-2xl">{data.name}</div>
                    <div className="mb-2">{data.description}</div>            
                    <div className="flex align-items-center">
                        <i className="pi pi-tag mr-2"></i>
                        <span className="font-semibold">{data.category}</span>
                    </div>
                </div>
                <div className="flex flex-row md:flex-column justify-content-between w-full md:w-auto align-items-center md:align-items-end mt-5 md:mt-0">
                    <span className="text-2xl font-semibold mb-2 align-self-center md:align-self-end">{data.publish_date}</span>
                    <Button icon="pi pi-pencil" label="Edit donation" disabled={data.status === 'END' || data.status === 'ONGOING' || data.status === 'REQUESTED'} className="mb-2 p-button-sm"></Button>
                    <span className={`product-badge status-${data.status.toLowerCase()}`}>{data.status}</span>
                </div>
            </div>
        </div>
    );
};

const dataviewGridItem = (data) => {
    return (
        <div className="col-12 lg:col-4">
            <div className="card m-3 border-1 surface-border">
                <div className="flex flex-wrap gap-2 align-items-center justify-content-between mb-2">
                    <div className="flex align-items-center">
                        <i className="pi pi-tag mr-2" />
                        <span className="font-semibold">{data.category}</span>
                    </div>
                    <span className={`product-badge status-${data.status.toLowerCase()}`}>{data.status}</span>
                   
                </div>
                <div className="flex flex-column align-items-center text-center mb-3">
                    <img src={`images/product/${data.image}`} alt={data.name} className="h-40 w-8 shadow-2 my-3 mx-0" />
                    <div className="text-2xl font-bold">{data.name}</div>
                    <div className="mb-3">{data.description}</div>                    
                </div>
                <div className="flex align-items-center justify-content-between">
                    <span className="text-2xl font-semibold">{data.publish_date}</span>
                    <Button icon="pi pi-pencil" disabled={data.status === 'END' || data.status === 'ONGOING' || data.status === 'REQUESTED'} />                
                </div>
            </div>
        </div>
    );
};

const itemTemplate = (data, layout) => {
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
  <h1>Hello, <b>UserName</b></h1>
    <div className="grid list-demo">
        <div className="col-12">
            <div className="card">
                <h5>User donations</h5>
                <DataView value={filteredValue || dataViewValue} layout={layout} paginator rows={12} sortOrder={sortOrder} sortField={sortField} itemTemplate={itemTemplate} header={dataViewHeader}></DataView>
            </div>
        </div>
    </div>
    </>
);
}

export default MyDonations;

