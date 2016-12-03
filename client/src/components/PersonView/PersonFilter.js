import './PersonFilter.css';
import React from 'react';
import { connect } from 'react-redux'
import { Card, Chip, Button } from 'react-materialize';
import * as d3 from 'd3';
import * as chromatic from 'd3-scale-chromatic';



import actions from '../../state/actions';
import { genderConceptIdToGender } from '../../utils/enums';



const colorScale = d3.scaleOrdinal(chromatic.schemePastel1);
export default connect(mapStateToProps, mapDispatchToProps)(PersonFilter)

function PersonFilter({ filters, onFilterChanged }) {


    const filterCategories = [
        {
            displayName: 'Gender',
            id: 'genderId',
            options: getEnumValues(genderConceptIdToGender),
        },
        {
            displayName: 'Status',
            id: 'dead',
            options: [
                {
                    id: false,
                    displayName: 'Alive',
                },
                {
                    id: true,
                    displayName: 'Dead',
                }
            ],
        }
    ];

    return (
        <Card>
            <div className="person-filter">
            {filterCategories.map(toFilterCategories)}
            </div>
        </Card>
  );

    function toFilterCategories(category) {
        return (
            <div key={category.id} className="filter-category">
                <span>{category.displayName}</span>
                <div>{category.options.map(toFilterOptions)}</div>
            </div>
        );

        function toFilterOptions(option, index) {
            const isSelected = filters[category.id] === option.id;
            return (
                <Button
                    key={option.id}
                    className={"filter-option " + (isSelected ? 'selected' : '') }
                    onClick={ () => onFilterChanged(category, option.id) }>{option.displayName}</Button>
            );
        }
    }



}

function mapStateToProps(state) {
  return {
       filters: state.filters,
      //searchText: state.searchText,
  };
}

function mapDispatchToProps(dispatch) {
  return {
    // onFilterChanged,
  };

}


function getEnumValues(enumeration) {
    return Object.keys(enumeration).map(toValue);

    function toValue(id) {
        return {
            id,
            displayName: enumeration[id],
        };
    }
}