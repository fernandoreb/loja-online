FROM openjdk:8

# Create app directory
WORKDIR /jbpm-server-7.28.0.Final-dist

# Bundle app source
COPY . .

# Expose the ports we're interested in
EXPOSE 8080 9990

# Set the default command to run on boot
# This will boot WildFly in the standalone mode and bind to all interface
CMD [ "./bin/standalone.sh", "-b","0.0.0.0" ]