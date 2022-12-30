import { Card } from "primereact/card";
import { DashboardProps } from "../types/DashboardProps";

const Dashboard = (props: DashboardProps) => {
  const menuCard1 = (
    <img alt="Card" src={props.images ? props.images[0] : ""} />
  );
  const menuCard2 = (
    <img alt="Card" src={props.images ? props.images[1] : ""} />
  );
  const menuCard3 = (
    <img alt="Card" src={props.images ? props.images[2] : ""} />
  );

  return (
    <>
      <div className="grid grid-cols-3 gap-36 place-content-center pt-16">
        <Card
          className="w-96 h-2/3 cursor-pointer p-4"
          header={menuCard1}
          onClick={props.onClickMenus[0]}
        >
          <div className="flex justify-center">
            <h2>{props.titles ? props.titles[0] : ""}</h2>
          </div>
        </Card>
        <Card
          className="w-96 h-2/3 cursor-pointer p-4"
          header={menuCard2}
          onClick={props.onClickMenus[1]}
        >
          <div className="flex justify-center">
            <h2>{props.titles ? props.titles[1] : ""}</h2>
          </div>
        </Card>
        <Card
          className="w-96 h-2/3 cursor-pointer p-4"
          header={menuCard3}
          onClick={props.onClickMenus[2]}
        >
          <div className="flex justify-center">
            <h2>{props.titles ? props.titles[2] : ""}</h2>
          </div>
        </Card>
      </div>
    </>
  );
};

export default Dashboard;
