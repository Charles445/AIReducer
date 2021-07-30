function initializeCoreMod() {
	var Opcodes = Java.type("org.objectweb.asm.Opcodes");
	var ASMAPI = Java.type("net.minecraftforge.coremod.api.ASMAPI");
	
	return {
		"Entity#collide": {
			"target": {
				"type": "METHOD",
				"class": "net.minecraft.entity.Entity",
				"methodName": "func_213306_e",
				"methodDesc": "(Lnet/minecraft/util/math/vector/Vector3d;)Lnet/minecraft/util/math/vector/Vector3d;"
			},
			"transformer": function(methodNode) {
				var instructions = methodNode.instructions;
				var getEntityCollisions_name = ASMAPI.mapMethod("func_230318_c_");
				for(i = 0; i < instructions.size() ; i++) {
					var instruction = instructions.get(i);
					if(instruction.getOpcode() == Opcodes.INVOKEVIRTUAL) {
						if(instruction.name.equals(getEntityCollisions_name)) {
							//Found getEntityCollisions INVOKEVIRTUAL
							instruction.setOpcode(Opcodes.INVOKESTATIC);
							instruction.owner = "com/charles445/aireducer/util/CollisionUtil";
							instruction.name = "getEntityCollisions";
							instruction.desc = "(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;Ljava/util/function/Predicate;)Ljava/util/stream/Stream;";
						}
					}
				}
				return methodNode;
			}		
		},
		"LivingEntity#pushEntities": {
			"target": {
				"type": "METHOD",
				"class": "net.minecraft.entity.LivingEntity",
				"methodName": "func_85033_bc",
				"methodDesc": "()V"
			},
			"transformer": function(methodNode) {
				var instructions = methodNode.instructions;
				var getEntities_name = ASMAPI.mapMethod("func_175674_a");
				for(i = 0; i < instructions.size() ; i++) {
					var instruction = instructions.get(i);
					if(instruction.getOpcode() == Opcodes.INVOKEVIRTUAL) {
						if(instruction.name.equals(getEntities_name)) {
							//Found getEntityCollisions INVOKEVIRTUAL
							instruction.setOpcode(Opcodes.INVOKESTATIC);
							instruction.owner = "com/charles445/aireducer/util/CollisionUtil";
							instruction.name = "getEntities";
							instruction.desc = "(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/AxisAlignedBB;Ljava/util/function/Predicate;)Ljava/util/List;";
						}
					}
				}
				return methodNode;
			}
		}
	};
}
