import { AppContext } from "next/app";
import router from "next/router";
import { Button } from "primereact/button";
import {
  DataView,
  DataViewLayoutOptions,
  DataViewLayoutType,
} from "primereact/dataview";
import { Toast } from "primereact/toast";
import { useEffect, useRef, useState } from "react";
import { getFinishedDonations } from "../backend/Donation";
import AppMenuBar from "../components/AppMenuBar";
import { Pagination } from "../types/Pagination";

const FinishedDonations = (props: any) => {
  // Finished Donations items
  const [finishedDataViewValue, setFinishedDataViewValue] = useState<any>(null);
  // If is a grid or a list
  const [layout, setLayout] = useState<DataViewLayoutType>("grid");
  //for display messages in the view
  const toast = useRef<any>(null);

  // load the donations from the backend
  useEffect(() => {
    setFinishedDataViewValue(props.donations);
  });

  const goToPage = (page: string) => {
    router.push(page);
  };

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
              onClick={() => goToPage("donation/" + data.id)}
              icon="pi pi-info-circle"
              label="Edit donation"
              className="mb-2 p-button-sm bg-orange-300 border-orange-400"
            ></Button>
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
          <div className="flex align-items-center justify-content-between">
            <span className="text-2xl font-semibold">
              {data.createdDate.substring(0, 10)}
            </span>
            <div>
              <Button
                onClick={() => goToPage("donation/" + data.id)}
                icon="pi pi-info-circle"
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
            <h3 className="text-red-600">Finished Donations</h3>
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
};

export async function getServerSideProps(context: AppContext) {
  const pagination: Pagination = await getFinishedDonations();

  return {
    props: { donations: pagination.results },
  };
}

export default FinishedDonations;
