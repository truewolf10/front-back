import {PhaseTemplateModel} from './phase-template.model';

export interface ProjectPhaseModel {
  id: number;
  name: string;
  description : string;
  statusPhase : Map<String,String>;
  templates? : Array<PhaseTemplateModel>;
}
