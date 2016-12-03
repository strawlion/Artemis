import React from 'react';
import logo from './logo.svg';
import './App.css';

import PersonView from './PersonView';
import Sidebar from './Sidebar';


export default function App() {
  return (
    <div className="App" style={{ background: 'rgba(235,238,241,.85)', width: '100%', height: '100%', display: 'flex', position: 'absolute' }}>
      <Sidebar />
      <div className="person-view">
        <PersonView />
      </div>
    </div>
  );
}