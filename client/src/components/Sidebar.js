import React from 'react';

import './Sidebar.css'

export default function Sidebar() {
    return (
        <div className="sidebar">
            <div className="logo-container">
                <span className="material-icons logo">pie_chart</span>
                <span className="logo-text">Artemis</span>
            </div>
            <ul className="menu-category-container">
                <li>
                    <span>Visualize</span>
                    <ul>
                        <li className="sub-category selected">
                            <i className="fa fa-user sub-category-icon" aria-hidden="true"></i>
                            <span>Person</span>
                        </li>
                        <li className="sub-category">
                            <i className="fa fa-line-chart sub-category-icon" aria-hidden="true"></i>
                            <span>Observations</span>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
  );
}