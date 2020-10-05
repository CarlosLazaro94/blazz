
import React, {  Component  } from 'react';
import { render } from 'react-dom';
import { AgGridColumn, AgGridReact } from 'ag-grid-react';
import 'ag-grid-community/dist/styles/ag-grid.css';
import 'ag-grid-community/dist/styles/ag-theme-alpine.css';

class App extends Component {
  constructor(props) {
    super(props);

    this.state = {
     
      columnDefs: [
        {
          field: 'id',
          minWidth: 170,
          checkboxSelection: checkboxSelection,
          headerCheckboxSelection: headerCheckboxSelection,
        },
        { field: 'first_name' },
        { field: 'last_name' },
        { field: 'email' },
        { field: 'phone' },
      ],
      autoGroupColumnDef: {
        headerName: 'Group',
        minWidth: 170,
        field: 'id',
        valueGetter: function (params) {
          if (params.node.group) {
            return params.node.key;
          } else {
            return params.data[params.colDef.field];
          }
        },
        headerCheckboxSelection: true,
        cellRenderer: 'agGroupCellRenderer',
        cellRendererParams: { checkbox: true },
      },
      defaultColDef: {
        editable: true,
        enableRowGroup: true,
        enablePivot: true,
        enableValue: true,
        sortable: true,
        resizable: true,
        filter: true,
        flex: 1,
        minWidth: 100,
      },
      rowSelection: 'multiple',
      rowGroupPanelShow: 'always',
      pivotPanelShow: 'always',
      rowData: [],
    };
  }

  onGridReady = (params) => {
    this.gridApi = params.api;
    this.gridColumnApi = params.columnApi;

    const httpRequest = new XMLHttpRequest();
    const updateData = (data) => {
      this.setState({ rowData: data });
    };

    httpRequest.open(
      'GET',
      'https://heroku-app-111-222.herokuapp.com/api/persons'
    );
    httpRequest.send();
    httpRequest.onreadystatechange = () => {
      if (httpRequest.readyState === 4 && httpRequest.status === 200) {
        updateData(JSON.parse(httpRequest.responseText));
      }
    };
  };

  render() {
    return (
      
      <div style={{ width: '100%', height: '100%' }}>
        <h1>List of User</h1>
        <div
          id="myGrid"
          style={{width:700, height:600}}
          className="ag-theme-alpine"
        >
          <AgGridReact
            
            columnDefs={this.state.columnDefs}
            autoGroupColumnDef={this.state.autoGroupColumnDef}
            defaultColDef={this.state.defaultColDef}
            suppressRowClickSelection={true}
            groupSelectsChildren={true}
            debug={true}
            rowSelection={this.state.rowSelection}
            rowGroupPanelShow={this.state.rowGroupPanelShow}
            pivotPanelShow={this.state.pivotPanelShow}
            enableRangeSelection={true}
            pagination={true}
            onGridReady={this.onGridReady}
            rowData={this.state.rowData}
          />
        </div>
      </div>
    );
  }
}
 
var checkboxSelection = function (params) {
  return params.columnApi.getRowGroupColumns().length === 0;
};
var headerCheckboxSelection = function (params) {
  return params.columnApi.getRowGroupColumns().length === 0;
};



export default App;
