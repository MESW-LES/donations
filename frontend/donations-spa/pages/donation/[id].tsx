import AppMenuBar from ".././AppMenuBar";
import axios from "axios";
import React, { useRef, useState , useEffect} from "react";
import { useRouter } from "next/router";
import { Galleria } from 'primereact/galleria';

/* 
export const getStaticPaths = async () =>{
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    };
    const res = await fetch('http://localhost:8080/donations', requestOptions);
    const data = await res.json();
    console.log(data);

     paths = data.data.map(donation =>{
        return{
params:{id:donation.id.toString() }
        }
    })

    return {paths, fallback:false} 
}

export async function getStaticProps() {
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    };
    const res =  await fetch('http://localhost:8080/donations' + 1, requestOptions)
    const data = await res.json();
    
    return{
        props: {donation: data}
    }
    
} */
 
function DetailDonation() {
    const [donation, setDonation] = useState<any>(null);
    const router = useRouter()
    const id = router.query.id

    const responsiveOptions = [
        {
            breakpoint: '1024px',
            numVisible: 5
        },
        {
            breakpoint: '768px',
            numVisible: 3
        },
        {
            breakpoint: '560px',
            numVisible: 1
        }
    ];

    useEffect(() => {
        fetchDonation();
    }, []); 
    
    
    //Fetch data from the BackEnd
    const fetchDonation = async ()=>{

        try {
            const requestOptions = {
                method: 'GET',
                headers: { 'Content-Type': 'application/json' }
            };
             const response = await fetch('http://localhost:8080/donations/'+id, requestOptions)
             const data = await response.json();
             setDonation(data.message)
             console.log(data);
             
          } catch (error) {
            if(error instanceof Error){
                 console.log(error);    
        }
    }
      
        
      }

    return (
      <>
        <AppMenuBar />      
        <div className="card">
        <h1>Detail of: {donation.title}</h1>
        </div>
        <div>
        <Galleria value={images} item={itemTemplate} thumbnail={thumbnailTemplate} numVisible={5} responsiveOptions={responsiveOptions}></Galleria>
        </div>
        
        <h1>{id}</h1>
      </>
    );
  }
  
  export default DetailDonation;