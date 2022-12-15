import AppMenuBar from ".././AppMenuBar";
import axios from "axios";
import React, { useRef, useState, useEffect } from "react";
import { useRouter } from "next/router";
import { Galleria } from "primereact/galleria";
import { Divider } from "primereact/divider";
import { Splitter, SplitterPanel } from "primereact/splitter";
import { Accordion, AccordionTab } from "primereact/accordion";
import { Button } from "primereact/button";

function DetailDonation({ donation }: any) {
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
        style={{ width: "100%" }}
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

  return (
    <>
      <div>
        <AppMenuBar />
      </div>

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
              style={{ maxWidth: "640px" }}
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
                  <p>Category: {donation.categories}</p>
                </AccordionTab>
              </Accordion>
              <div className="align-items-center justify-content-center pt-3">
                <Button
                  className="col-10"
                  icon="pi pi-shopping-cart"
                  label="Request"
                  disabled={donation.donationProcess.status !== "Created"}
                  onClick={() => console.log("")}
                />
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
