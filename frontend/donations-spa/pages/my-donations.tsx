import AppMenuBar from "../components/AppMenuBar";
import React, { useState, useEffect, useRef } from "react";
import {
  DataView,
  DataViewLayoutOptions,
  DataViewLayoutType,
} from "primereact/dataview";
import { Button } from "primereact/button";
import { Dropdown } from "primereact/dropdown";
import { InputText } from "primereact/inputtext";
import { useRouter } from "next/router";
import axios from "axios";
import { Toast } from "primereact/toast";
import { getCreatedDonations, getFinishedDonations } from "../backend/Donation";

function MyDonations() {
  // Created Donations items
  const [createdDataViewValue, setCreatedDataViewValue] = useState<any>(null);
  // Finished Donations items
  const [finishedDataViewValue, setFinishedDataViewValue] = useState<any>(null);
  // If is a grid or a list
  const [layout, setLayout] = useState<DataViewLayoutType>("grid");
  // Option that is selected to sort
  const [sortKey, setSortKey] = useState(null);
  //for display messages in the view
  const toast = useRef<any>(null);

  const router = useRouter();

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

  //Created donations
  const fetchCreatedDonations = async () => {
    const createdDonations = await getCreatedDonations();
    setCreatedDataViewValue(createdDonations.results);
  };

  //Created donations
  const fetchFinishedDonations = async () => {
    const finishedDonations = await getFinishedDonations();
    setFinishedDataViewValue(finishedDonations.results);
  };

  //Fetch delete donation BackEnd
  const deleteDonation = async (id: any) => {
    try {
      const { data } = await axios({
        url: `http://localhost:8080/donations/${id}`,
        method: "DELETE",
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
          "The donation was deleted successfully."
        );
        fetchCreatedDonations();
      }
    } catch (error) {
      if (error instanceof Error) {
        showToast("error", "Error " + error.message, error.message);
      }
    }
  };

  useEffect(() => {
    /* const productService = new ProductService();
    productService.getProducts().then((data) => setDataViewValue(data)); */
    fetchCreatedDonations();
    fetchFinishedDonations();
  }, []);

  const dataViewHeader = (
    <div className="flex flex-column md:flex-row md:justify-content-between gap-2">
      <DataViewLayoutOptions
        layout={layout}
        onChange={(e) => setLayout(e.value)}
      />
    </div>
  );

  const dataviewListItem = (data: any) => {
    return (
      <div className="col-12">
        <div className="flex flex-column md:flex-row align-items-center p-3 w-full">
          <img
            src={`http://localhost:8080/donationsImages/${data.donationImages[0]}.png`}
            alt={data.title}
            className="my-4 md:my-0 w-9 md:w-10rem shadow-2 mr-5"
          />
          <div className="flex-1 flex flex-column align-items-center text-center md:text-left">
            <div className="font-bold text-2xl">{data.title}</div>
            <div className="mb-2">{data.description}</div>
            <div className="flex align-items-center">
              <i className="pi pi-tag mr-2"></i>
              <span className="font-semibold">
                {data.categories[0].name.toUpperCase()}
              </span>
            </div>
          </div>
          <div className="flex flex-row md:flex-column justify-content-between w-full md:w-auto align-items-center md:align-items-end mt-5 md:mt-0">
            <span className="text-2xl font-semibold mb-2 align-self-center md:align-self-end">
              {data.createdDate}
            </span>
            <Button
              onClick={() => {
                deleteDonation(data.id);
              }}
              icon="pi pi-trash"
              label="Delete donation"
              className={`mb-2 bg-red-300 border-red-400 p-button-sm ${
                data.donationProcess.status.toUpperCase() === "FINISHED"
                  ? "invisible"
                  : "visible"
              }`}
            ></Button>
            <Button
              onClick={() => goToPage(`donation/${data.id}/edit`)}
              icon="pi pi-pencil"
              label="Edit donation"
              className={`mb-2 p-button-sm ${
                data.donationProcess.status.toUpperCase() === "FINISHED"
                  ? "invisible"
                  : "visible"
              }`}
            />
            <Button
              onClick={() => goToPage(`donation/${data.id}`)}
              icon="pi pi-info-circle"
              label="Donation process"
              className=" mb-2 p-button-sm bg-orange-300 border-orange-400 "
            />
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
              <span className="font-semibold">
                {data.categories[0].name.toUpperCase()}
              </span>
            </div>
          </div>
          <div className="flex flex-column align-items-center text-center mb-3">
            <img
              src={`http://localhost:8080/donationsImages/${data.donationImages[0]}.png`}
              alt={data.title}
              className="h-40 w-8 shadow-2 my-3 mx-0"
            />
            <div className="text-2xl font-bold">{data.title}</div>
            <div className="mb-3">{data.description}</div>
          </div>
          <div className="flex justify-between">
            <span className="text-2xl font-semibold">
              {data.createdDate.substring(0, 10)}
            </span>
            <div className="flex gap-2">
              <Button
                onClick={() => {
                  deleteDonation(data.id);
                }}
                icon="pi pi-trash"
                className={`bg-red-300 border-red-400 ${
                  data.donationProcess.status.toUpperCase() === "FINISHED"
                    ? "invisible"
                    : "visible"
                }`}
              ></Button>
              <Button
                onClick={() => goToPage(`donation/${data.id}/edit`)}
                icon="pi pi-pencil"
                className={`${
                  data.donationProcess.status.toUpperCase() === "FINISHED"
                    ? "invisible"
                    : "visible"
                }`}
              />
              <Button
                onClick={() => goToPage(`donation/${data.id}`)}
                icon="pi pi-info-circle"
                disabled={data.donationProcess.status.toUpperCase() === "END"}
                className=" bg-orange-300 border-orange-400 "
              />
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

    if (layout === "list") {
      return dataviewListItem(data);
    } else if (layout === "grid") {
      return dataviewGridItem(data);
    }
  };

  return (
    <>
      <AppMenuBar />
      <Toast ref={toast} />
      <div className="grid list-demo">
        <div className="col-12 p-4">
          <div className="card">
            <div className="flex justify-between">
              <h3 className="text-lime-600">Created Donations</h3>
              <Button
                className=" flex bg-green-500 border-transparent font-semibold"
                icon="pi pi-fw pi-box"
                label="Add Donation"
                onClick={() => goToPage("add_donation")}
              ></Button>
            </div>
            <DataView
              value={createdDataViewValue}
              layout={layout}
              paginator
              rows={12}
              itemTemplate={itemTemplate}
              header={dataViewHeader}
            ></DataView>
          </div>
        </div>
      </div>

      <div className="grid list-demo">
        <div className="col-12 p-4">
          <div className="card">
            <div className="flex">
              <h3 className="text-red-600">Finished Donations</h3>
            </div>
            <DataView
              value={finishedDataViewValue}
              layout={layout}
              paginator
              rows={12}
              itemTemplate={itemTemplate}
              header={dataViewHeader}
            ></DataView>
          </div>
        </div>
      </div>
    </>
  );
}

export default MyDonations;
