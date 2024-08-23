import { Button, Col, Row, Stack, Table } from "react-bootstrap";


export default function Ambulances () {

    return(
        <Col>
            {/* <Row><h1>Komponente</h1></Row>
            <FormCheck>
                <FormCheckInput name="pretraga" onChange={(e) => setChecked(e.target.checked)}></FormCheckInput>
                <FormLabel htmlFor="pretraga">Prikazi pretragu</FormLabel>
            </FormCheck>
            {checked && <Search searchParamsCallback={(searchParams) => setSearchParams(searchParams)} 
                getKomponenteCallback={(firstPage) => getKomponente(firstPage)} searchParams={searchParams} proizvodjaci={proizvodjaci}/>}
            <br></br> */}
            <Row className="justify-content-center" style={{marginTop:150}}>
            <Col md={8} >
                <Table striped bordered hover >
                    <thead>
                        <tr>
                            <th>Logo</th>
                            <th>Name</th>
                            <th>Phone numbers</th>
                            <th>Links</th>
                            <th>Adress</th>
                            {/* {props.loginInfo?.isAdmin&& <th></th> }
                            {props.loginInfo?.isAdmin  && <th></th> }
                            {props.loginInfo?.isAdmin  && <th></th> }
                            {props.loginInfo?.isAdmin&& <th></th> } */}
                        </tr>
                    </thead>
                    <tbody>
                        {}
                    </tbody>
                </Table>
            </Col></Row>
            {/* <Stack direction="horizontal" gap={3}>
                {props.loginInfo?.isAdmin ? <Button className="button button-navy" onClick={() => goToAdd()}>Add</Button> : <></>}
                <Button className="ms-auto" disabled={pageNo === 0} onClick={() => getKomponente(pageNo - 1)}>Prev</Button>
                {komponente.length === 0 ? pageNo : pageNo + 1}/{pageCount}
                <Button disabled={pageNo === pageCount - 1} onClick={() => getKomponente(pageNo + 1)}>Next</Button>               
            </Stack> */}
        </Col>
    )
}