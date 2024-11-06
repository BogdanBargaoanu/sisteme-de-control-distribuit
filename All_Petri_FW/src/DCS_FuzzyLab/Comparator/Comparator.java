package DCS_FuzzyLab.Comparator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataFuzzy;
import DataOnly.FLRS;
import DataOnly.FV;
import DataOnly.Fuzzy;
import DataOnly.FuzzyVector;
import DataOnly.PlaceNameWithWeight;
import Enumerations.FZ;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Comparator {
	public static void main (String[]args) throws FileNotFoundException {
	PetriNet pn = new PetriNet();
	pn.PetriNetName = "Comparator";
	pn.NetworkPort = 1081;
	
	FLRS differentiator = new FLRS(new FV(FZ.ZR), new FV(FZ.NM), new FV(FZ.NL), new FV(FZ.NL),new FV(FZ.NL), 
								   new FV(FZ.PM), new FV(FZ.ZR), new FV(FZ.NM), new FV(FZ.NL), new FV(FZ.NL), 
								   new FV(FZ.PL), new FV(FZ.PM), new FV(FZ.ZR), new FV(FZ.NM), new FV(FZ.NL), 
								   new FV(FZ.PL), new FV(FZ.PL), new FV(FZ.PM), new FV(FZ.ZR), new FV(FZ.NM),
								   new FV(FZ.PL), new FV(FZ.PL), new FV(FZ.PL), new FV(FZ.PM),	new FV(FZ.ZR));
	
	FLRS separator = new FLRS(new FV(FZ.NL,FZ.FF), new FV(FZ.NL,FZ.FF), new FV(FZ.FF,FZ.FF), new FV(FZ.FF,FZ.PL),new FV(FZ.FF,FZ.PL));

	differentiator.Print();
	separator.Print();
	
	pn.SetInputFile("D:\\PetriInputData\\comparator3.txt");
	
	DataFuzzy p0 = new DataFuzzy();
	p0.SetName("P0");
	p0.SetValue(new Fuzzy(0.1F));
	pn.PlaceList.add(p0);
	
	DataFuzzy p1 = new DataFuzzy();
	p1.SetName("P1");
	p1.SetValue(new Fuzzy(0.2F));
	pn.PlaceList.add(p1);

	DataFuzzy p2 = new DataFuzzy();
	p2.SetName("P2");
	pn.PlaceList.add(p2);

	DataFuzzy p3 = new DataFuzzy();
	p3.SetName("P3");
	pn.PlaceList.add(p3);

	DataFuzzy p4 = new DataFuzzy();
	p4.SetName("P4");
	pn.PlaceList.add(p4);
	
	// T0 ------------------------------------------------
			PetriTransition t0 = new PetriTransition(pn);
			t0.TransitionName = "T0";
			t0.InputPlaceName.add("P0");
			t0.InputPlaceName.add("P1");

			Condition T0Ct1 = new Condition(t0, "P0", TransitionCondition.NotNull);
			Condition T0Ct2 = new Condition(t0, "P1", TransitionCondition.NotNull);
			T0Ct1.SetNextCondition(LogicConnector.AND, T0Ct2);

			GuardMapping grdT0 = new GuardMapping();
			grdT0.condition = T0Ct1;

			ArrayList<PlaceNameWithWeight> input0 = new ArrayList<>();
			input0.add(new PlaceNameWithWeight("P0", 1F));
			input0.add(new PlaceNameWithWeight("P1", 1F));

			ArrayList<String> Output0 = new ArrayList<>();
			Output0.add("P2");
			

			grdT0.Activations.add(new Activation(t0, differentiator, input0, TransitionOperation.FLRS, Output0));
			
			t0.GuardMappingList.add(grdT0);

			t0.Delay = 0;
			pn.Transitions.add(t0);
			
			
			// T1 ------------------------------------------------
			PetriTransition t1 = new PetriTransition(pn);
			t1.TransitionName = "T1";
			t1.InputPlaceName.add("P2");

			Condition T1Ct1 = new Condition(t1, "P2", TransitionCondition.NotNull);

			GuardMapping grdT1 = new GuardMapping();
			grdT1.condition = T1Ct1;

			ArrayList<PlaceNameWithWeight> input1 = new ArrayList<>();
			input1.add(new PlaceNameWithWeight("P2", 1F));

			
			ArrayList<String> Output1 = new ArrayList<>();
			Output1.add("P3");
			Output1.add("P4");

			
			grdT1.Activations.add(new Activation(t1, separator, input1, TransitionOperation.FLRS, Output1));
			
			t1.GuardMappingList.add(grdT1);

			t1.Delay = 0;
			pn.Transitions.add(t1);

			// -------------------------------------------

			// PetriTransition t3 = new PetriTransition(pn);
			// pn.Transitions.add(t3);

			System.out.println("Comparator started \n ------------------------------");
			pn.Delay = 0;
			pn.PrintingSpeed=10;

			pn.ShowLogInWindow=false;
			// pn.Start();

			PetriNetWindow frame = new PetriNetWindow(false);
			frame.petriNet = pn;
			frame.setVisible(true);
	}
}
