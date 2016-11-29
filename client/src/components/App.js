import React from 'react';
import logo from './logo.svg';
import './App.css';

import PersonView from './PersonView';
import Sidebar from './Sidebar';


export default function App() {
  return (
    <div className="App" style={{ background: 'rgba(235,238,241,.85)', width: '100%', height: '100%', display: 'flex', position: 'absolute' }}>
      <Sidebar />
      <div style={{ display: 'flex', width: '100%', alignItems: 'center', justifyContent: 'center' }}>
        <PersonView />
      </div>
    </div>
  );
}

// <div className="content" style={{ position: 'absolute', bottom: '10px', top: '10px', right: '10px', left: '210px', background: 'white', border: 'solid 1px #dfe0e4' }}>
      // </div>