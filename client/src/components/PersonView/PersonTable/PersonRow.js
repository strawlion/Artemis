import React from 'react';
import { CollapsibleItem, Preloader } from 'react-materialize';

export default function PersonRow({ person, onClick }) {

    let observationsTemplate = <Preloader />;
    if (person.observations && person.observationPeriods) {
        observationsTemplate = (
            <div>
                <div>Observation Periods: { person.observationPeriods.map(toObservationPeriodDisplayValue).join(', ')}</div>
                <div>Observations: { person.observations.map(toObservationDisplayValue).join(', ') }</div>
            </div>
        );
    }
    return (
        <CollapsibleItem header={person.name} icon='perm_identity' onSelect={ () => onClick(person) }>
            <div>Gender: {person.gender}</div>
            <div>Race: {person.race}</div>
            <div>Ethnicity: {person.ethnicity}</div>
            <div>Born: {person.dateOfBirth.format('MMMM Do, YYYY')}</div>
            {observationsTemplate}
        </CollapsibleItem>
  );
}

function toObservationDisplayValue(observation) {
    return observation.date.format('MMMM Do, YYYY');
}

function toObservationPeriodDisplayValue(observationPeriod) {
    return observationPeriod.startDate.format('MMMM Do, YYYY') + ' - ' + observationPeriod.endDate.format('MMMM Do, YYYY')
}
