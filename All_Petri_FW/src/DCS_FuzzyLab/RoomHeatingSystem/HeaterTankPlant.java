package DCS_FuzzyLab.RoomHeatingSystem;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataFuzzy;
import DataObjects.DataTransfer;
import DataOnly.FLRS;
import DataOnly.FV;
import DataOnly.Fuzzy;
import DataOnly.FuzzyVector;
import DataOnly.PlaceNameWithWeight;
import DataOnly.TransferOperation;
import Enumerations.FZ;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

/*				The Equations are:
*               xNew = a * x + b * c + d1 * v1 + d2 * v2;
*               The constants are:
*				a = 1.0f;
*			    b = 0.01f;
*				d1 = 0.01f;
*				d2 = 0.00055f;
*/
public class HeaterTankPlant {
	public static void main(String[] args) throws FileNotFoundException {
		PetriNet pn = new PetriNet();
		pn.PetriNetName = "Heater Tank Plant";
		pn.NetworkPort = 1081;

		DataFuzzy x = new DataFuzzy();
		x.SetName("x");
		x.SetValue(new Fuzzy(0.96F)); // start temp 24 and temp range -25 - 25
		pn.PlaceList.add(x);

		DataFuzzy c = new DataFuzzy(); // from Heater Tank Plant
		c.SetName("c");
		c.SetValue(new Fuzzy(0.0F));
		pn.PlaceList.add(c);

		DataFuzzy v1 = new DataFuzzy(); // outside temp
		v1.SetName("v1");
		pn.PlaceList.add(v1);

		DataFuzzy p1 = new DataFuzzy();
		p1.SetName("p1");
		pn.PlaceList.add(p1);

		DataFuzzy p2 = new DataFuzzy();
		p2.SetName("p2");
		pn.PlaceList.add(p2);

		DataFuzzy p3 = new DataFuzzy();
		p3.SetName("p3");
		pn.PlaceList.add(p3);

		DataTransfer p_o2 = new DataTransfer();
		p_o2.SetName("p_o2");
		p_o2.Value = new TransferOperation("localhost", "1082", "P3"); // to RTC
		pn.PlaceList.add(p_o2);

		// T11 ------------------------------------------------
		PetriTransition t_11 = new PetriTransition(pn);
		t_11.TransitionName = "t_11";
		t_11.InputPlaceName.add("v");

		Condition T_11Ct1 = new Condition(t_11, "v", TransitionCondition.NotNull);

		GuardMapping grdt_11 = new GuardMapping();
		grdt_11.condition = T_11Ct1;

		ArrayList<PlaceNameWithWeight> input1 = new ArrayList<>();
		input1.add(new PlaceNameWithWeight("v", 0.01F)); // window disturbance
		input1.add(new PlaceNameWithWeight("v", 0.00055F)); // wall disturbance

		grdt_11.Activations.add(new Activation(input1, TransitionOperation.Add_Fuzzy, "p1", t_11));

		t_11.GuardMappingList.add(grdt_11);

		t_11.Delay = 0;
		pn.Transitions.add(t_11);

		// T12 ------------------------------------------------
		PetriTransition t_12 = new PetriTransition(pn);
		t_12.TransitionName = "t_12";
		t_12.InputPlaceName.add("c");
		t_12.InputPlaceName.add("p1");

		Condition T_12Ct1 = new Condition(t_12, "y", TransitionCondition.NotNull);
		Condition T_12Ct2 = new Condition(t_12, "p1", TransitionCondition.NotNull);
		T_12Ct1.SetNextCondition(LogicConnector.AND, T_12Ct2);

		GuardMapping grdt_12 = new GuardMapping();
		grdt_12.condition = T_12Ct1;

		ArrayList<PlaceNameWithWeight> input2 = new ArrayList<>();
		input2.add(new PlaceNameWithWeight("c", 0.01F));
		input2.add(new PlaceNameWithWeight("p1", 1.0F));

		grdt_12.Activations.add(new Activation(input1, TransitionOperation.Add_Fuzzy, "p2", t_12));

		t_12.GuardMappingList.add(grdt_12);

		t_12.Delay = 0;
		pn.Transitions.add(t_12);

		// T13 ------------------------------------------------
		PetriTransition t_13 = new PetriTransition(pn);
		t_13.TransitionName = "t_13";
		t_13.InputPlaceName.add("c");
		t_13.InputPlaceName.add("p1");

		Condition T_13Ct1 = new Condition(t_13, "y", TransitionCondition.NotNull);
		Condition T_13Ct2 = new Condition(t_13, "p1", TransitionCondition.NotNull);
		T_13Ct1.SetNextCondition(LogicConnector.AND, T_13Ct2);

		GuardMapping grdt_13 = new GuardMapping();
		grdt_13.condition = T_13Ct1;

		ArrayList<PlaceNameWithWeight> input3 = new ArrayList<>();
		input3.add(new PlaceNameWithWeight("c", 0.01F));
		input3.add(new PlaceNameWithWeight("p1", 1.0F));

		grdt_13.Activations.add(new Activation(input1, TransitionOperation.Add_Fuzzy, "p2", t_13));

		t_13.GuardMappingList.add(grdt_13);

		t_13.Delay = 0;
		pn.Transitions.add(t_13);

		// T14 ------------------------------------------------
		PetriTransition t_14 = new PetriTransition(pn);
		t_14.TransitionName = "t_14";
		t_14.InputPlaceName.add("x");
		t_14.InputPlaceName.add("p2");

		Condition T_14Ct1 = new Condition(t_14, "x", TransitionCondition.NotNull);
		Condition T_14Ct2 = new Condition(t_14, "p2", TransitionCondition.NotNull);
		T_14Ct1.SetNextCondition(LogicConnector.AND, T_14Ct2);

		GuardMapping grdt_14 = new GuardMapping();
		grdt_14.condition = T_14Ct1;

		ArrayList<PlaceNameWithWeight> input4 = new ArrayList<>();
		input4.add(new PlaceNameWithWeight("x", 1.0F));
		input4.add(new PlaceNameWithWeight("p2", 1.0F));

		grdt_14.Activations.add(new Activation(input1, TransitionOperation.Add_Fuzzy, "p3", t_14));

		t_14.GuardMappingList.add(grdt_14);

		t_14.Delay = 0;
		pn.Transitions.add(t_14);

		// T15 ------------------------------------------------
		PetriTransition t_15 = new PetriTransition(pn);
		t_15.TransitionName = "t_15";
		t_15.InputPlaceName.add("p3");

		Condition T_15Ct1 = new Condition(t_15, "p3", TransitionCondition.NotNull);

		GuardMapping grdt_15 = new GuardMapping();
		grdt_15.condition = T_15Ct1;

		grdt_15.Activations.add(new Activation(t_15, "p3", TransitionOperation.SendOverNetwork, "p_o2"));

		t_15.GuardMappingList.add(grdt_14);

		t_15.Delay = 0;
		pn.Transitions.add(t_15);

		// --------------------------------------------------------

		// PetriTransition t3 = new PetriTransition(pn);
		// pn.Transitions.add(t3);

		System.out.println("Plant started \n ------------------------------");
		pn.Delay = 0;
		// pn.Start();

		PetriNetWindow frame = new PetriNetWindow(false);
		frame.petriNet = pn;
		frame.setVisible(true);
	}
}
