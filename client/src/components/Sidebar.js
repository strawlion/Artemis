import React from 'react';

export default function Sidebar() {
    return (
        <div className="sidebar" style={{ width: '230px', height: '100%', flex: '0 0 230px', }}>
            <div style={{ marginTop: '20px' }}>
                <span className="material-icons" style={{ color: '#fd5c63', verticalAlign: 'top', fontSize: '34px' }}>pie_chart</span>
                <span style={{ fontSize: '26px', letterSpacing: '3px', fontWeight: 'bold', fontFamily: "'Ubuntu', Helvetica, Arial, sans-serif", marginLeft: '5px' }}>Artemis</span>
            </div>
            <ul style={{ color: '#a898b0', display: 'block', margin: '0 0 5px 5px', marginTop: '100px', fontSize: '13px', fontWeight: 400, }}>
                <li>
                    <span>Perspective</span>
                    <ul>
                        <li>Person</li>
                    </ul>
                </li>
            </ul>
        </div>
  );
}