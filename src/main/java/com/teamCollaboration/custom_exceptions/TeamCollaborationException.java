package com.teamCollaboration.custom_exceptions;

@SuppressWarnings("serial")
public class TeamCollaborationException extends RuntimeException{
public TeamCollaborationException(String message) {
	super(message);
}
}
