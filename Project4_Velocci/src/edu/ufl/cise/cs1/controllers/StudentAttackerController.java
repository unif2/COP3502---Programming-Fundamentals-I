package edu.ufl.cise.cs1.controllers;
import game.controllers.AttackerController;
import game.models.*;
import java.util.List;
import java.util.ArrayList;

public final class StudentAttackerController implements AttackerController {

	public void init(Game game) { }

	public void shutdown(Game game) { }

	public int update(Game game,long timeDue) {
	
		int action = -1; // Direction attacker will move next, to be returned by this method.

		Attacker attacker = game.getAttacker(); // Attacker to control.
		Node attackerLocation = attacker.getLocation(); // Attacker's location.

		List<Node> powerPillLocations = game.getPowerPillList(); // Locations of available power pills.
		List<Node> pillLocations = game.getPillList(); // Locations of available pills.
		Node closestPill = attacker.getTargetNode(pillLocations, true); // Closest pill.
		int distToClosestPill = attackerLocation.getPathDistance(closestPill); // Distance to closest pill.

		List<Defender> defenders = game.getDefenders(); // List of defenders.
		List<Defender> vulDefenders = new ArrayList<Defender>(); // List of defenders currently vulnerable.

		// Populate list of vulnerable defenders.
		for (Defender d: defenders) {
			if (d.isVulnerable()) {
				vulDefenders.add(d);
			}
		}

		Actor closestDefender = attacker.getTargetActor(defenders, true); // Defender closest to attacker.
		Node closestDefenderLocation = closestDefender.getLocation(); // Location of closest defender.
		int distToClosestDef = attackerLocation.getPathDistance(closestDefenderLocation); // Distance to closest def.

		// Closest defender is close to attacker (within 5 nodes) and is non-vulnerable.
		if (attackerLocation.getPathDistance(closestDefenderLocation) < 5 &&
		!(((Defender) closestDefender).isVulnerable())) {
			// If there are no power pills, or the closest one is far away, attacker flees the closest defender.
			if (powerPillLocations.isEmpty() || (!powerPillLocations.isEmpty() &&
					attackerLocation.getPathDistance(attacker.getTargetNode(powerPillLocations, true)) > 5)) {
				action = attacker.getNextDir(closestDefenderLocation, false);
				return action;
			}
		}

		// If there are vulnerable defenders, attacker chases the closest vulnerable defender.
		if (!vulDefenders.isEmpty()) {
			Actor closestVulnerableDefender = attacker.getTargetActor(vulDefenders, true);
			action = attacker.getNextDir(closestVulnerableDefender.getLocation(), true);
			return action;
		}

		// If there are power pills, attacker gets close to one, but only eats it when a defender is very close.
		if (!powerPillLocations.isEmpty()) {
			Node closestPowerPill = attacker.getTargetNode(powerPillLocations, true); // Closest power pill.
			int distToClosestPowerPill = attackerLocation.getPathDistance(closestPowerPill); // Distance from it.

			// If non-vulnerable defender is close to attacker, and attacker is close to power pill, attacker eats it.
			if ((distToClosestDef <= 1) && !((Defender) closestDefender).isVulnerable() && distToClosestPowerPill <= 1) {
				action = attacker.getNextDir(closestPowerPill, true);
				return action;
			}

			// Defender isn't too close to attacker and attacker is close to a power pill, attacker remains still.
			else if (distToClosestPowerPill <= 1 && distToClosestDef > 4) {
				// Oscillate back and forth to effectively make attacker stay at this location.
				action = attacker.getReverse();
				return action;
			}

			// Else, attacker moves towards the closest power pill.
			else {
				action = attacker.getNextDir(closestPowerPill, true);
				return action;
			}
		}

		// If attacker is closer to the closest pill than to the closest defender, or closest defender is far away.
		if (distToClosestPill <= distToClosestDef || distToClosestDef > 5 ) {
			// Attacker moves towards the closest pill.
			action = attacker.getNextDir(closestPill, true);
			return action;
		}

		// Attacker is closer to the closest defender than to the closest pill, or the closest defender is too close.
		else {
			// Attacker flees the closest defender.
			action = attacker.getNextDir(closestDefenderLocation, false);
			return action;
		}
	}
}