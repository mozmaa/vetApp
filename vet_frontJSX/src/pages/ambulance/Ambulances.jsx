import { Button, Col, Form, Row, Stack, Table } from "react-bootstrap";

import AmbulanceRow from "./AmbulanceRow";
import { useDispatch, useSelector } from "react-redux";
import { useEffect, useState } from "react";
import { fetchAmbulanceData } from "../../store/ambulance/ambulance_actions";

export default function Ambulances() {
  const [searchParams, setSearchParams] = useState({
    name: "",
    city: "",
    isClosed: false,
    pageNo: 0,
  });

  const ambulances = useSelector((state) => state.ambulances);
  const dispatch = useDispatch();
  const { ambulancesArray, pageNo, pageCount } = ambulances;

  useEffect(() => {
    dispatch(fetchAmbulanceData(searchParams));
  }, [searchParams, dispatch]);

  if (!Array.isArray(ambulancesArray)) {
    return null;
  }

  const handleSearchChange = (event, pageNo) => {
    if(pageNo !== searchParams.pageNo){
      setSearchParams((prev) => ({ ...prev, pageNo: event }));
      return
    }
    const { name, value } = event.target;
    setSearchParams((prev) => ({ ...prev, [name]: value, pageNo: pageNo }));
  };

  return (
    <Col>
      <Row className="justify-content-center" style={{ marginTop: 150 }}>
        <Col md={9}>
          <Table striped bordered hover>
            <thead>
              <tr style={{ borderStyle: "none" }}>
                <th style={{ borderStyle: "none" }}></th>
                <th style={{ borderStyle: "none" }}>
                  <Form.Control
                    type="text"
                    name="name"
                    placeholder="Search by name"
                    onChange={handleSearchChange}
                  />
                </th>
                <th style={{ borderStyle: "none" }}></th>
                <th style={{ borderStyle: "none" }}></th>
                <th style={{ borderStyle: "none" }}>
                  <Form.Control
                    type="text"
                    name="city"
                    placeholder="Search by city"
                    onChange={handleSearchChange}
                  />
                </th>
              </tr>
              <tr>
                <th>Logo</th>
                <th>Name</th>
                <th>Phone numbers</th>
                <th>Links</th>
                <th>Adress</th>
                <th></th>
              </tr>
            </thead>
            <AmbulanceRow />
          </Table>
          <Stack direction="horizontal" gap={3}>
            <Button
              className="ms-auto"
              disabled={pageNo === 0}
              onClick={() => handleSearchChange(pageNo - 1)}
            >
              Prev
            </Button>
            {ambulancesArray.length === 0 ? pageNo : pageNo + 1}/{pageCount}
            <Button
              disabled={pageNo === pageCount - 1}
              onClick={() => handleSearchChange(pageNo + 1)}
            >
              Next
            </Button>
          </Stack>
        </Col>
      </Row>
    </Col>
  );
}
