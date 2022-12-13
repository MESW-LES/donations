import AppMenuBar from ".././AppMenuBar";
import axios from "axios";
import React, { useRef, useState , useEffect} from "react";
import { useRouter } from "next/router";

/* export const getStaticPaths = async () =>{
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    };
    const res = await fetch('http://localhost:8080/donations', requestOptions);
    const data = await res.json();
    console.log(data);

    const paths = data.map((donation:any) =>{
        return{
params:{id:donation.id.toString() }
        }
    })

    return {paths, fallback:false}
}

export const getStaticProps = async(context: any) =>{
    const id = context.params.id;
    const requestOptions = {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    };
    const res =  await fetch('http://localhost:8080/donations' + id, requestOptions)
    const data = await res.json();
    
    return{
        props: {donation: data}
    }
    
}
 */
function DetailDonation() {
    const router = useRouter()
    const id = router.query.id

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
             console.log(data);
             
            /* console.log("Debuggggg");
            if(!Number.isNaN(Number(router.query.id))){
                const { data } = await axios({
                    url: "http://localhost:8080/donation/"+router.query.id,        
                    method: "GET",
                    headers: { 'Content-Type': 'application/json' }
                  });
            if (data.code != 200) {    
            } else {
              console.log("OK");
              console.log(data);
            }} */
          } catch (error) {
            if(error instanceof Error){
                 console.log(error);    
        }
    }
        //const response = await fetch('/api/donations');
        //const data = await response.json();
        //console.log(data.data.message.results);
        //console.log(data.code);
      
        
      }

    return (
      <>
        <AppMenuBar />        
        <h1>Detail Donation</h1>
        <h1>{id}</h1>
      </>
    );
  }
  
  export default DetailDonation;