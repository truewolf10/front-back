import {ProjectPhaseModel} from './projectPhase.model';

export interface ProjectModel {
  id : number;
  name : string;
  size : string;
  description : string;
  phasesDtos : ProjectPhaseModel[];
}
