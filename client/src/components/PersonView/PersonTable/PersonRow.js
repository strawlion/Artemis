import React from 'react';
import { CollapsibleItem, Preloader } from 'react-materialize';

import dxCodeToDescription from '../../../utils/dxCodeToDescription';

import './PersonRow.css';

export default function PersonRow({ person, onClick }) {

    let observationsTemplate = <div className="person-details-bottom-container"><Preloader size='small' /></div>;
    if (person.observations && person.observationPeriods) {
        observationsTemplate = (
            <div className="person-details-bottom-container">
                <div className="person-detail">
                    <div className="person-detail-title">Observation Periods</div>
                    <div className="person-detail-value">{ person.observationPeriods.map(toObservationPeriodDisplayValue).join('\n')}</div>
                </div>
                <div className="person-detail">
                    <div className="person-detail-title">Observations</div>
                    <div className="person-detail-value person-detail-observations-value">{ person.observations.map(toObservationDisplayValue).join('\n') }</div>
                </div>
            </div>
        );
    };

    const deathTemplate = person.dateOfDeath ? (
        <div className="person-detail">
            <div className="person-detail-title">Died</div>
            <div className="person-detail-value">{person.dateOfDeath.format('MMMM Do, YYYY')}</div>
        </div>
    ) : null;
    return (
        <CollapsibleItem header={person.name} icon='perm_identity' onSelect={ () => onClick(person) }>
            <div className="person-details-container">
                <div className="person-details-top-container">
                    <div className="person-detail">
                        <div className="person-detail-title">Gender</div>
                        <div className="person-detail-value">{person.gender}</div>
                    </div>
                    <div className="person-detail">
                        <div className="person-detail-title">Race</div>
                        <div className="person-detail-value">{person.race}</div>
                    </div>
                    <div className="person-detail">
                        <div className="person-detail-title">Ethnicity</div>
                        <div className="person-detail-value">{person.ethnicity}</div>
                    </div>
                    <div className="person-detail">
                        <div className="person-detail-title">Born</div>
                        <div className="person-detail-value">{person.dateOfBirth.format('MMMM Do, YYYY')}</div>
                    </div>
                    {deathTemplate}
                </div>
                {observationsTemplate}
            </div>
        </CollapsibleItem>
  );
}

function toObservationDisplayValue(observation) {
    return observation.date.format('MMMM Do, YYYY') + ' - ' + (dxCodeToDescription[observation.observationSourceValue] || observation.observationSourceValue);
}

function toObservationPeriodDisplayValue(observationPeriod) {
    return observationPeriod.startDate.format('MMMM Do, YYYY') + ' - ' + observationPeriod.endDate.format('MMMM Do, YYYY')
}
