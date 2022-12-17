import AppMenuBar from ".././AppMenuBar";
import axios from "axios";
import React, { useRef, useState, useEffect } from "react";
import { useRouter } from "next/router";
import { Galleria } from "primereact/galleria";
import { Divider } from "primereact/divider";
import { Splitter, SplitterPanel } from "primereact/splitter";
import { Accordion, AccordionTab } from "primereact/accordion";
import { Button } from "primereact/button";
import { Toast } from "primereact/toast";

function DetailDonation({ donation }: any) {
  //for display messages in the view
const toast = useRef<any>(null);
  const router = useRouter();
  const id = router.query.id;
  console.log(router.query.id);
  const [images, setImages] = useState<any>(null);
  //const [donation, setDonation] = useState<any>(null);

  const itemTemplate = (item: string) => {
    console.log("DEBUG123");
    console.log(item);
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

  useEffect(() => {
    const id = router.query.id;
    console.log(id);
    fetchImages();
    console.log(images);
  }, []);

  const fetchImages = () => {
    console.log(donation.donationImages);
    setImages(donation.donationImages);
    //return donation.donationImages;
  };

    //Fetch request donation BackEnd
const requestDonation = async (id: any)=>{
  try {
      const { data } = await axios({
        url: `http://localhost:8080/donations/${id}/request`,        
        method: "PUT",
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
          "The donation was requested successfully."
        );
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
}

    //Fetch request donation BackEnd
    const acceptDonation = async (id: any)=>{
      try {
        const accept = {
          "decision": true
      }
          const { data } = await axios.put(`http://localhost:8080/donations/${id}/decision`, accept);
    
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
              "The donation was accepted successfully."
            );
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
    }

        //Fetch request donation BackEnd
const rejectDonation = async (id: any)=>{
  try {
    const reject = {
      "decision": false
  }
      const { data } = await axios.put(`http://localhost:8080/donations/${id}/decision`, reject);

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
          "The donation was rejected successfully."
        );
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
}

  return (
    <>
      <div>
        <AppMenuBar />
      </div>
      <Toast ref={toast} />
      <div className="card col-12 pt-10">
        <div className="pb-4">
          <h2>Detail of: {donation.title} </h2>
          <Divider type="solid" />
        </div>
        <Splitter className="mb-5">
          <SplitterPanel className="flex align-items-center justify-content-center">
            <Galleria
              value={images}
              item={itemTemplate}
              thumbnail={thumbnailTemplate}            
              numVisible={5}
              responsiveOptions={responsiveOptions}
              style={{ maxWidth: "600px" }}
            ></Galleria>
          </SplitterPanel>
          <SplitterPanel className="flex align-items-center justify-content-center col-12">
            <div className="flex-column align-items-center justify-content-center col-11">
              <Accordion
                multiple
                activeIndex={[0]}
                className="col-10 align-items-center justify-content-center"
              >
                <AccordionTab tabIndex={0} header="Description">
                  <p>{donation.description}</p>
                </AccordionTab>
                <AccordionTab tabIndex={1} header="Details">
                  <p>Created date: {donation.createdDate}</p>
                  <p>Donation status: {donation.donationProcess.status}</p>
                  <p>Category: {donation.categories.map((data : any)=>{
                    return data.name
                  })}</p>
                </AccordionTab>
              </Accordion>
              <div className="align-items-center justify-content-center pt-3">
                <Button
                  className="col-10"
                  icon="pi pi-shopping-cart"
                  label="Request"
                  disabled={donation.donationProcess.status !== "Created"}
                  onClick={() => requestDonation(donation.id)}
                />
                <div className="flex align-items-center  pt-3">
                     <Button
                  className="col-5 mr-1 bg-green-400 border-green-500"
                  icon="pi pi-check"
                  label="Accept"
                  disabled={donation.donationProcess.status !== "Requested"}
                  onClick={() => acceptDonation(donation.id)}
                />
                  <Button
                  className="col-5 ml-1 bg-red-400 border-red-500"
                  icon="pi pi-times"
                  label="Reject"
                  disabled={donation.donationProcess.status !== "Requested"}
                  onClick={() => rejectDonation(donation.id)}
                />
                </div>
              </div>
            </div>
          </SplitterPanel>
        </Splitter>
      </div>
      <h2></h2>
    </>
  );
}

export default DetailDonation;

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
