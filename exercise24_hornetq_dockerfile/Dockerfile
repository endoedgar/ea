FROM mansante/hornetq
MAINTAINER edgar <endoedgarjunior@gmail.com>

COPY hornetq-jms.xml ${HORNETQ_HOME}/config/stand-alone/non-clustered/

WORKDIR $HORNETQ_HOME/bin

EXPOSE 5445 1098 1099

CMD ["./run.sh"]
